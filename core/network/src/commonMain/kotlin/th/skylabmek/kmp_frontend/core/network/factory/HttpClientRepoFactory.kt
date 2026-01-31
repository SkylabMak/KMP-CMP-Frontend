package th.skylabmek.kmp_frontend.core.network.factory

import th.skylabmek.kmp_frontend.core.network.config.NetworkConfig
import th.skylabmek.kmp_frontend.core.network.repository.HttpClientRepo
import th.skylabmek.kmp_frontend.core.network.repository.SharedHttpClientRepo
import th.skylabmek.kmp_frontend.core.network.repository.StatelessHttpClientRepo

class HttpClientRepoFactory(
    private val defaultNetworkConfig: NetworkConfig
) {
    fun createSharedHttpClientRepo(config: NetworkConfig = defaultNetworkConfig): HttpClientRepo {
        return SharedHttpClientRepo(config)
    }

    fun createStatelessHttpClientRepo(config: NetworkConfig = defaultNetworkConfig): HttpClientRepo {
        return StatelessHttpClientRepo(config)
    }
}
