package th.skylabmek.kmp_frontend.domain.model.feature

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import th.skylabmek.kmp_frontend.shared_resources.Res
import th.skylabmek.kmp_frontend.shared_resources.*

@Serializable
enum class FeatureCode {
    @SerialName("LIFE_STATUS")
    LIFE_STATUS,

    @SerialName("PROJECTS")
    PROJECTS,

    @SerialName("BUG_REPORT")
    BUG_REPORT,

    @SerialName("DIRECT_CONTACT")
    DIRECT_CONTACT,

    @SerialName("SHOW_PERFORMANCE")
    SHOW_PERFORMANCE,

    @SerialName("SHOW_PROFILE_INFO")
    SHOW_PROFILE_INFO,

    @SerialName("UNKNOWN")
    UNKNOWN;

    fun getNameRes(): StringResource = when (this) {
        LIFE_STATUS -> Res.string.feature_name_life_status
        PROJECTS -> Res.string.feature_name_projects
        BUG_REPORT -> Res.string.feature_name_bug_report
        DIRECT_CONTACT -> Res.string.feature_name_direct_contact
        SHOW_PERFORMANCE -> Res.string.feature_name_show_performance
        SHOW_PROFILE_INFO -> Res.string.feature_name_show_profile_info
        UNKNOWN -> Res.string.feature_name_unknown
    }

    companion object {
        fun fromCode(code: String): FeatureCode {
            return entries.find { it.name == code } ?: UNKNOWN
        }
    }
}
