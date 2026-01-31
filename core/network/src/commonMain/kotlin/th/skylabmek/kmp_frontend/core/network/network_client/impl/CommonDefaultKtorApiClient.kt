package th.skylabmek.kmp_frontend.core.network.network_client.impl

import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.encodedPath
import io.ktor.http.isSuccess
import io.ktor.http.takeFrom
import th.skylabmek.kmp_frontend.core.network.config.NetworkConfig
import th.skylabmek.kmp_frontend.core.network.client.HttpClientProvider
import th.skylabmek.kmp_frontend.core.network.network_client.NetworkClient
import th.skylabmek.kmp_frontend.core.network.request.RequestSpec
import th.skylabmek.kmp_frontend.core.network.result.NetworkError
import th.skylabmek.kmp_frontend.core.network.result.NetworkResult
import th.skylabmek.kmp_frontend.core.network.result.toNetworkError

class CommonDefaultKtorApiClient(
    private val httpClientProvider: HttpClientProvider,
    private val networkConfig: NetworkConfig
) : NetworkClient {

    override suspend fun <T> execute(
        reqSpec: RequestSpec,
        mapper: suspend (HttpResponse) -> T
    ): NetworkResult<T> {
        return try {
            val response = httpClientProvider.getHttpClient().request {
                method = reqSpec.method
                url {
                    takeFrom(networkConfig.baseUrl)
                    val path = if (reqSpec.path.startsWith("/")) reqSpec.path else "/${reqSpec.path}"
                    encodedPath = path
                }
                headers.appendAll(io.ktor.http.Headers.build {
                    networkConfig.defaultHeaders.forEach { (k, v) -> append(k, v) }
                    reqSpec.headers.forEach { (k, v) -> append(k, v) }
                })
                reqSpec.body?.let { setBody(it) }
            }

            if (response.status.isSuccess()) {
                NetworkResult.Success(mapper(response))
            } else {
                NetworkResult.Failure(
                    NetworkError.Http(
                        code = response.status.value,
                        message = response.status.description
                    )
                )
            }

        } catch (e: Throwable) {
            NetworkResult.Failure(e.toNetworkError())
        }
    }
}
