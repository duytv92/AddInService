package vn.netstars.addin.imedia.topup.utils.constants

import vn.netstars.addin.imedia.topup.R


object MappingConst {
    val SERVER_ERROR_RESPONSE_MAPPING = mutableMapOf(
        ResponseResultCode.CONNECTION_ERROR to R.string._nss_err_CONNECTION,
        ResponseResultCode.SOCKET_TIMEOUT to R.string._nss_err_SOCKET_TIMEOUT
    )
}