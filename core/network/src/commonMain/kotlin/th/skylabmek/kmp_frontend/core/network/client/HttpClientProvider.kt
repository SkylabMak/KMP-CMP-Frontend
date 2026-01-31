package th.skylabmek.kmp_frontend.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import th.skylabmek.kmp_frontend.core.network.config.NetworkConfig

interface HttpClientProvider {
    fun getHttpClient(): HttpClient
}

/**
 * Returns the same HttpClient instance every time.
 */
class SharedHttpClientProvider(
    private val config: NetworkConfig
) : HttpClientProvider {
    private val client: HttpClient by lazy {
        createClient(config)
    }

    override fun getHttpClient(): HttpClient = client
}

/**
 * Creates and returns a new HttpClient instance every time it's called.
 */
class StatelessHttpClientProvider(
    private val config: NetworkConfig
) : HttpClientProvider {
    override fun getHttpClient(): HttpClient = createClient(config)
}

private fun createClient(config: NetworkConfig): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = config.jsonConfig.ignoreUnknownKeys
                prettyPrint = config.jsonConfig.prettyPrint
                isLenient = config.jsonConfig.isLenient
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = config.timeoutMillis
            connectTimeoutMillis = config.connectTimeoutMillis
            socketTimeoutMillis = config.socketTimeoutMillis
        }
        if (config.isDebug) {
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}
