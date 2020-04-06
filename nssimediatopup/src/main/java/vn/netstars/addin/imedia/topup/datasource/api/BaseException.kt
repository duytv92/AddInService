package vn.netstars.addin.imedia.topup.datasource.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response

class BaseException(
    val errorType: ErrorType,
    val serverErrorResponse: ServerErrorResponse? = null,
    val response: Response<*>? = null,
    val httpCode: String = "",
    cause: Throwable? = null
) : RuntimeException(cause?.message, cause) {

    override val message: String?
        get() = when (errorType) {
            ErrorType.HTTP -> response?.message()

            ErrorType.NETWORK -> cause?.message

            ErrorType.SERVER -> serverErrorResponse?.message // TODO update real response

            ErrorType.UNEXPECTED -> cause?.message
        }

    companion object {
        fun toHttpError(response: Response<*>, httpCode: String) =
            BaseException(
                errorType = ErrorType.HTTP,
                response = response,
                httpCode = httpCode
            )

        fun toNetworkError(cause: Throwable) =
            BaseException(
                errorType = ErrorType.NETWORK,
                cause = cause
            )

        fun toServerError(serverErrorResponse: ServerErrorResponse, httpCode: String) =
            BaseException(
                errorType = ErrorType.SERVER,
                serverErrorResponse = serverErrorResponse,
                httpCode = httpCode
            )

        fun toUnexpectedError(cause: Throwable) =
            BaseException(
                errorType = ErrorType.UNEXPECTED,
                cause = cause
            )
    }
}

/**
 * Identifies the error type which triggered a [BaseException]
 */
enum class ErrorType {
    NETWORK,
    HTTP,
    SERVER,
    UNEXPECTED
}

// TODO update server error response
data class ServerErrorResponse(
    @SerializedName("errCode")
    val code: String? = null,

    @SerializedName("errMessage")
    val message: String? = null
)