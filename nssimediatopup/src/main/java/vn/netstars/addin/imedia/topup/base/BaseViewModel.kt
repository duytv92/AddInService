package vn.starpay.lite.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vn.netstars.addin.imedia.topup.datasource.api.BaseException
import vn.netstars.addin.imedia.topup.utils.constants.ResponseResultCode
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<String>()

    open fun onLoadFail(throwable: Throwable) {
        when (throwable.cause) {
            is UnknownHostException -> {
                errorMessage.value = ResponseResultCode.CONNECTION_ERROR
            }
            is SocketTimeoutException -> {
                errorMessage.value = ResponseResultCode.SOCKET_TIMEOUT
            }
            is ConnectException -> {
                errorMessage.value = ResponseResultCode.CONNECTION_ERROR
            }
            else -> {
                when (throwable) {
                    is BaseException -> {
                        if (throwable.serverErrorResponse == null) {
                            errorMessage.value = throwable.message
                        } else {
                            if (throwable.serverErrorResponse.message.isNullOrBlank()) {
                                errorMessage.value = throwable.message
                            } else {
                                errorMessage.value = throwable.serverErrorResponse.message
                            }
                        }
                    }
                    else -> {
                        errorMessage.value = throwable.message
                    }
                }
            }
        }
    }

    open fun processServerResult(result: String) {
        errorMessage.value = result
    }

    open fun onLoadSuccess() {
        errorMessage.value = null
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }

    fun getLoading() = isLoading
    fun getErrorMessage() = errorMessage
}