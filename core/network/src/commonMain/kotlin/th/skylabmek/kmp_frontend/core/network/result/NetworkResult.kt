package th.skylabmek.kmp_frontend.core.network.result

import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import kotlinx.io.IOException
import org.jetbrains.compose.resources.StringResource
import th.skylabmek.kmp_frontend.shared_resources.Res
import th.skylabmek.kmp_frontend.shared_resources.error_timeout
import th.skylabmek.kmp_frontend.shared_resources.error_no_internet
import th.skylabmek.kmp_frontend.shared_resources.error_server_generic
import th.skylabmek.kmp_frontend.shared_resources.error_unknown

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure(val error: NetworkError) : NetworkResult<Nothing>()
}

sealed class NetworkError {
    data object Timeout : NetworkError()
    data object NetworkUnavailable : NetworkError()
    data class Http(val code: Int, val message: String?) : NetworkError()
    data class Unknown(val cause: Throwable) : NetworkError()

    fun asStringResource(): StringResource = when (this) {
        is Timeout -> Res.string.error_timeout
        is NetworkUnavailable -> Res.string.error_no_internet
        is Http -> Res.string.error_server_generic
        is Unknown -> Res.string.error_unknown
    }
}

public fun Throwable.toNetworkError(): NetworkError =
    when (this) {
        is HttpRequestTimeoutException -> NetworkError.Timeout
        is IOException -> NetworkError.NetworkUnavailable
        is ResponseException -> NetworkError.Http(
            code = response.status.value,
            message = response.status.description
        )
        else -> NetworkError.Unknown(this)
    }
