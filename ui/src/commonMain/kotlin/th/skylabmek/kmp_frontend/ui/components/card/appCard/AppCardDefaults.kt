package th.skylabmek.kmp_frontend.ui.components.card.appCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import th.skylabmek.kmp_frontend.ui.config.UI

/**
 * Default values for AppCard components.
 */
object AppCardDefaults {

    /**
     * Default rounded shape for all cards.
     * Using 16.dp corner radius for a modern, friendly look.
     */
    val shape: Shape
        @Composable
        get() = MaterialTheme.shapes.large // 16.dp rounded corners

    /**
     * Alternative shape options
     */
    val extraRoundedShape: Shape
        @Composable
        get() = MaterialTheme.shapes.extraLarge // 28.dp rounded corners

    val mediumRoundedShape: Shape
        @Composable
        get() = MaterialTheme.shapes.medium // 12.dp rounded corners

    /**
     * Elevation values in Dp for easier shadow control.
     */
    val defaultElevation = 1.dp
    val elevatedElevation = 4.dp
    val outlinedElevation = 0.dp

    /**
     * Default colors for standard card.
     */
    @Composable
    fun cardColors(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = 0.38f),
        disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    ): CardColors = CardDefaults.cardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    /**
     * Default colors for outlined card.
     */
    @Composable
    fun outlinedCardColors(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = 0.38f),
        disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    ): CardColors = CardDefaults.outlinedCardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    /**
     * Default colors for elevated card.
     */
    @Composable
    fun elevatedCardColors(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = 0.38f),
        disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    ): CardColors = CardDefaults.elevatedCardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    /**
     * Default elevation for standard card.
     */
    @Composable
    fun cardElevation(
        defaultElevation: Dp = AppCardDefaults.defaultElevation,
        pressedElevation: Dp = 2.dp,
        focusedElevation: Dp = 2.dp,
        hoveredElevation: Dp = 3.dp,
        draggedElevation: Dp = 4.dp,
        disabledElevation: Dp = 0.dp
    ): CardElevation = CardDefaults.cardElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        draggedElevation = draggedElevation,
        disabledElevation = disabledElevation
    )

    /**
     * Default elevation for outlined card.
     */
    @Composable
    fun outlinedCardElevation(
        defaultElevation: Dp = AppCardDefaults.outlinedElevation,
        pressedElevation: Dp = 0.dp,
        focusedElevation: Dp = 0.dp,
        hoveredElevation: Dp = 1.dp,
        draggedElevation: Dp = 2.dp,
        disabledElevation: Dp = 0.dp
    ): CardElevation = CardDefaults.outlinedCardElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        draggedElevation = draggedElevation,
        disabledElevation = disabledElevation
    )

    /**
     * Default elevation for elevated card.
     */
    @Composable
    fun elevatedCardElevation(
        defaultElevation: Dp = AppCardDefaults.elevatedElevation,
        pressedElevation: Dp = 4.dp,
        focusedElevation: Dp = 4.dp,
        hoveredElevation: Dp = 6.dp,
        draggedElevation: Dp = 8.dp,
        disabledElevation: Dp = 0.dp
    ): CardElevation = CardDefaults.elevatedCardElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        draggedElevation = draggedElevation,
        disabledElevation = disabledElevation
    )

    /**
     * Default border for outlined card.
     */
    @Composable
    fun outlinedCardBorder(
        borderColor: Color = MaterialTheme.colorScheme.outline,
        borderWidth: Dp = 1.dp
    ): BorderStroke = BorderStroke(
        width = borderWidth,
        color = borderColor
    )

    /**
     * Default shadow color for the card's elevation.
     * Uses MaterialTheme.colorScheme.scrim for consistency across the theme.
     * In dark theme, we increase the alpha to ensure it's visible against dark surfaces.
     */
    val shadowColor: Color
        @Composable
        get() = if (UI.isDark) {
            // Material 3 defines 'scrim' as the color for shadows/overlays.
            MaterialTheme.colorScheme.scrim.copy(alpha = 0.5f)
        } else {
            MaterialTheme.colorScheme.scrim.copy(alpha = 0.15f)
        }

    /**
     * Default ambient shadow color.
     */
    val ambientShadowColor: Color
        @Composable
        get() = if (UI.isDark) {
            MaterialTheme.colorScheme.scrim.copy(alpha = 0.4f)
        } else {
            MaterialTheme.colorScheme.scrim.copy(alpha = 0.1f)
        }
}
