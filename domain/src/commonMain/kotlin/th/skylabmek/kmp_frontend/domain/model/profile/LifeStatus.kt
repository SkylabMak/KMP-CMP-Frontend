package th.skylabmek.kmp_frontend.domain.model.profile

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LifeStatus(
    val name: String,
    val description: String? = null,
    @SerialName("color_token") val colorToken: String
) {
    val color: Color
        get() = LifeStatusColor.fromToken(colorToken).color
}

enum class LifeStatusColor(val token: String, val color: Color) {
    OPEN("OPEN", Color(0xFF4CAF50)),      // Green
    ENGAGED("ENGAGED", Color(0xFF2196F3)), // Blue
    STEADY("STEADY", Color(0xFF9C27B0)),   // Purple
    LIMITED("LIMITED", Color(0xFFFFC107)), // Amber
    BUSY("BUSY", Color(0xFFFF9800)),       // Orange
    PAUSED("PAUSED", Color(0xFF607D8B)),   // Blue Grey
    CLOSED("CLOSED", Color(0xFFF44336)),   // Red
    UNKNOWN("UNKNOWN", Color(0xFF9E9E9E)); // Grey

    companion object {
        fun fromToken(token: String): LifeStatusColor {
            return entries.find { it.token == token } ?: UNKNOWN
        }
    }
}