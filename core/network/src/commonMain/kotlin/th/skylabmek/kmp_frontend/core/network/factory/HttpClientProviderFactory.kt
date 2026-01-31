package th.skylabmek.kmp_frontend.core.network.factory

import th.skylabmek.kmp_frontend.core.network.config.NetworkConfig
import th.skylabmek.kmp_frontend.core.network.client.HttpClientProvider
import th.skylabmek.kmp_frontend.core.network.client.SharedHttpClientProvider
import th.skylabmek.kmp_frontend.core.network.client.StatelessHttpClientProvider

class HttpClientProviderFactory(
    private val defaultNetworkConfig: NetworkConfig
) {
    fun createSharedHttpClientProvider(config: NetworkConfig = defaultNetworkConfig): HttpClientProvider {
        return SharedHttpClientProvider(config)
    }

    fun createStatelessHttpClientProvider(config: NetworkConfig = defaultNetworkConfig): HttpClientProvider {
        return StatelessHttpClientProvider(config)
    }
}
