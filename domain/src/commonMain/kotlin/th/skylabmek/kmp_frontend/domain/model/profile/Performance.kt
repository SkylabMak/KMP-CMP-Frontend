package th.skylabmek.kmp_frontend.domain.model.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Performance(
    val id: String,
    @SerialName("profile_id") val profileId: String,
    @SerialName("category_id") val categoryId: String,
    @SerialName("visibility_id") val visibilityId: String,
    val title: String,
    val summary: String? = null,
    val content: String? = null,
    @SerialName("start_date") val startDate: String? = null,
    @SerialName("end_date") val endDate: String? = null,
    val location: String? = null,
    val close: Int = 0,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class PerformanceGroup(
    val category: String,
    val items: List<Performance>
)
