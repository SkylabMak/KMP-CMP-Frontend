package th.skylabmek.kmp_frontend.core.network.network_client

import io.ktor.client.call.body
import th.skylabmek.kmp_frontend.core.network.request.RequestSpec
import th.skylabmek.kmp_frontend.core.network.result.BaseResponse
import th.skylabmek.kmp_frontend.core.network.result.NetworkResult

/**
 * Executes a network request and automatically unwraps the backend's standard BaseResponse.
 * Expects the format: {"success": Boolean, "data": T?, "error": String?}
 */
suspend inline fun <reified T> NetworkClient.executeWrapped(
    reqSpec: RequestSpec
): NetworkResult<T> {
    return execute(
        reqSpec = reqSpec,
        mapper = { response ->
            val baseResponse = response.body<BaseResponse<T>>()
            if (baseResponse.success && baseResponse.data != null) {
                baseResponse.data
            } else {
                // Throwing here will be caught by the catch block in the execute implementation
                // and converted to a NetworkResult.Failure
                throw Exception(baseResponse.error ?: "Backend reported failure without error message")
            }
        }
    )
}
