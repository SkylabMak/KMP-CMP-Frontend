package th.skylabmek.kmp_frontend.domain.repository.feature

import th.skylabmek.kmp_frontend.core.network.result.NetworkResult
import th.skylabmek.kmp_frontend.domain.model.feature.FeatureStatusResponse

interface FeatureRepository {
    suspend fun getFeatureStatus(appID: String): NetworkResult<FeatureStatusResponse>
}