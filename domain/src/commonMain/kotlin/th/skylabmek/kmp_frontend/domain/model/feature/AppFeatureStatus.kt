package th.skylabmek.kmp_frontend.domain.model.feature

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppFeatureStatus(
    @SerialName("feature_code") val featureCode: FeatureCode,
    @SerialName("feature_name") val featureName: String,
    @SerialName("status_code") val statusCode: FeatureStatusCode,
    @SerialName("status_name") val statusName: String,
    @SerialName("is_closed") val isClosed: Boolean,
    @SerialName("updated_at") val updatedAt: String,
    val note: String? = null
)

@Serializable
data class FeatureStatusResponse(
    val features: List<AppFeatureStatus>
)
