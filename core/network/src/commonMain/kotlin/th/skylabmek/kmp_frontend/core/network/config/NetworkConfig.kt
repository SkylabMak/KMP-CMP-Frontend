package th.skylabmek.kmp_frontend.core.network.config

data class NetworkConfig(
    val baseUrl: String,
    val defaultHeaders: Map<String, String> = emptyMap(),
    val timeoutMillis: Long = 15_000,
    val connectTimeoutMillis: Long = 15_000,
    val socketTimeoutMillis: Long = 15_000,
    val isDebug: Boolean = false,
    val jsonConfig: JsonConfig = JsonConfig()
)

data class JsonConfig(
    val ignoreUnknownKeys: Boolean = true,
    val prettyPrint: Boolean = true,
    val isLenient: Boolean = true
)
