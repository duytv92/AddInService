package vn.netstars.addin.imedia.topup.datasource.api

import com.google.gson.Gson
import io.reactivex.*
import io.reactivex.functions.Function
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vn.netstars.addin.imedia.topup.BuildConfig
import vn.netstars.addin.imedia.topup.utils.constants.TimeoutConst
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class NITRetrofitClient private constructor() {
    val service: ApiService

    init {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(TimeoutConst.API_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS) //connect timeout
            .readTimeout(TimeoutConst.API_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS) //socket timeout
            .writeTimeout(TimeoutConst.API_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS) //write timeout
            .addInterceptor(logging)
            .build()

        val rxErrorHandlingCallAdapterFactory = RxErrorHandlingCallAdapterFactory()

        service = Retrofit.Builder()
            .baseUrl("https://demo8555066.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(rxErrorHandlingCallAdapterFactory)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    companion object {
        val instance: NITRetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NITRetrofitClient()
        }
    }
}


class RxErrorHandlingCallAdapterFactory : CallAdapter.Factory() {
    private val instance = RxJava2CallAdapterFactory.createAsync()

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? =
        RxCallAdapterWrapper(
            retrofit,
            instance.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>
        )
}

class RxCallAdapterWrapper<R>(
    private val retrofit: Retrofit,
    private val wrapped: CallAdapter<R, Any>
) : CallAdapter<R, Any> {

    override fun responseType(): Type = wrapped.responseType()

    override fun adapt(call: Call<R>): Any {
        val result = wrapped.adapt(call)
        return when (result) {
            is Single<*> -> {
                result.onErrorResumeNext(Function<Throwable, SingleSource<Nothing>> { throwable ->
                    Single.error(convertToBaseException(throwable))
                })
            }

            is Observable<*> -> {
                result.onErrorResumeNext(Function<Throwable, ObservableSource<Nothing>> { throwable ->
                    Observable.error(convertToBaseException(throwable))
                })
            }

            is Completable -> {
                result.onErrorResumeNext { throwable ->
                    Completable.error(convertToBaseException(throwable))
                }
            }

            is Flowable<*> -> {
                result.onErrorResumeNext(Function<Throwable, Flowable<Nothing>> { throwable ->
                    Flowable.error(convertToBaseException(throwable))
                })
            }

            is Maybe<*> -> {
                result.onErrorResumeNext(Function<Throwable, Maybe<Nothing>> { throwable ->
                    Maybe.error(convertToBaseException(throwable))
                })
            }

            else -> result
        }
    }

    private fun convertToBaseException(throwable: Throwable): BaseException =
        when (throwable) {
            is BaseException -> throwable

            is IOException -> BaseException.toNetworkError(throwable)

            is HttpException -> {
                val response = throwable.response()
                val httpCode = throwable.code().toString()

                if (response!!.errorBody() == null) {
                    BaseException.toHttpError(
                        httpCode = httpCode,
                        response = response
                    )
                }

                val serverErrorResponseBody = try {
                    response.errorBody()?.string() ?: ""
                } catch (e: Exception) {
                    ""
                }

                val serverErrorResponse =
                    try {
                        Gson().fromJson(serverErrorResponseBody, ServerErrorResponse::class.java)
                    } catch (e: Exception) {
                        ServerErrorResponse()
                    }

                if (serverErrorResponse != null) {
                    BaseException.toServerError(
                        serverErrorResponse = serverErrorResponse,
                        httpCode = httpCode
                    )
                } else {
                    BaseException.toHttpError(
                        response = response,
                        httpCode = httpCode
                    )
                }
            }

            else -> BaseException.toUnexpectedError(throwable)
        }
}