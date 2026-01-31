package th.skylabmek.kmp_frontend.core.network.di

import th.skylabmek.kmp_frontend.core.network.network_client.NetworkClient

data class WiredNetworkClient(
    val qualifier: NetworkQualifier,
    val networkClient: NetworkClient
)