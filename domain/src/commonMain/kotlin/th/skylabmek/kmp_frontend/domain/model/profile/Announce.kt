package th.skylabmek.kmp_frontend.domain.model.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Announce(
    val id: String,
    @SerialName("announce_type") val announceType: String,
    val title: String? = null,
    val message: String? = null,
    @SerialName("link_url") val linkUrl: String? = null,
    @SerialName("link_text") val linkText: String? = null,
    @SerialName("color_token") val colorToken: String? = null,
    @SerialName("starts_at") val startsAt: String? = null,
    @SerialName("ends_at") val endsAt: String? = null,
    @SerialName("created_at") val createdAt: String
)

@Serializable
data class AnnounceResponse(
    val items: List<Announce>
)