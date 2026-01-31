package th.skylabmek.kmp_frontend.domain.data.repository.feature

import io.ktor.http.HttpMethod
import th.skylabmek.kmp_frontend.core.network.network_client.NetworkClient
import th.skylabmek.kmp_frontend.core.network.network_client.executeWrapped
import th.skylabmek.kmp_frontend.core.network.request.RequestSpec
import th.skylabmek.kmp_frontend.core.network.result.NetworkResult
import th.skylabmek.kmp_frontend.domain.model.feature.FeatureStatusResponse
import th.skylabmek.kmp_frontend.domain.repository.feature.FeatureRepository

class FeatureRepositoryImpl(
    private val networkClient: NetworkClient
) : FeatureRepository {

    override suspend fun getFeatureStatus(appID: String): NetworkResult<FeatureStatusResponse> {
        return networkClient.executeWrapped<FeatureStatusResponse>(
            reqSpec = RequestSpec(
                method = HttpMethod.Get,
                path = "/features/$appID/feature-status"
            )
        )
    }
}