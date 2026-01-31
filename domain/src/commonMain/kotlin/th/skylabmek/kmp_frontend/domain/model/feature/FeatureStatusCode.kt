package th.skylabmek.kmp_frontend.domain.model.feature

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import th.skylabmek.kmp_frontend.shared_resources.Res
import th.skylabmek.kmp_frontend.shared_resources.*

@Serializable
enum class FeatureStatusCode {
    @SerialName("fs_operational")
    OPERATIONAL,

    @SerialName("fs_under_construction")
    UNDER_CONSTRUCTION,

    @SerialName("fs_maintenance")
    MAINTENANCE,

    @SerialName("fs_down")
    DOWN,

    @SerialName("fs_closed")
    CLOSED;

    fun getDisplayNameRes(): StringResource = when (this) {
        OPERATIONAL -> Res.string.feature_status_operational_name
        UNDER_CONSTRUCTION -> Res.string.feature_status_under_construction_name
        MAINTENANCE -> Res.string.feature_status_maintenance_name
        DOWN -> Res.string.feature_status_down_name
        CLOSED -> Res.string.feature_status_closed_name
    }

    fun getDescriptionRes(): StringResource = when (this) {
        OPERATIONAL -> Res.string.feature_status_operational_desc
        UNDER_CONSTRUCTION -> Res.string.feature_status_under_construction_desc
        MAINTENANCE -> Res.string.feature_status_maintenance_desc
        DOWN -> Res.string.feature_status_down_desc
        CLOSED -> Res.string.feature_status_closed_desc
    }

    fun getInstructionRes(): StringResource? = when (this) {
        MAINTENANCE -> Res.string.feature_status_maintenance_instruction
        DOWN -> Res.string.feature_status_down_instruction
        else -> null
    }
}
