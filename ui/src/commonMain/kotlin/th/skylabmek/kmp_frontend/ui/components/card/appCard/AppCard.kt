package th.skylabmek.kmp_frontend.ui.components.card.appCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardColors
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import th.skylabmek.kmp_frontend.ui.dimens.Dimens

/**
 * Standard rounded card component for the app.
 * All cards in the app should use this component for consistency.
 *
 * This component uses [Surface] and a custom shadow modifier to ensure
 * shadows are visible in both Light and Dark themes.
 */
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = AppCardDefaults.shape,
    colors: CardColors = AppCardDefaults.cardColors(),
    elevation: Dp = AppCardDefaults.defaultElevation,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    // Custom shadow modifier that uses theme-aware colors from AppCardDefaults.
    // This fixes the issue where shadows are invisible in dark theme against dark backgrounds.
    val shadowModifier = Modifier.shadow(
        elevation = elevation,
        shape = shape,
        clip = false,
        ambientColor = AppCardDefaults.ambientShadowColor,
        spotColor = AppCardDefaults.shadowColor
    )

    if (onClick != null) {
        Surface(
            onClick = onClick,
            modifier = modifier.then(shadowModifier),
            enabled = enabled,
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = elevation,
            shadowElevation = 0.dp, // We use our custom shadow modifier instead
            border = border
        ) {
            Column(content = content)
        }
    } else {
        Surface(
            modifier = modifier.then(shadowModifier),
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = elevation,
            shadowElevation = 0.dp, // We use our custom shadow modifier instead
            border = border
        ) {
            Column(content = content)
        }
    }
}

/**
 * Outlined variant of AppCard with border.
 */
@Composable
fun AppOutlinedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = AppCardDefaults.shape,
    colors: CardColors = AppCardDefaults.outlinedCardColors(),
    elevation: Dp = AppCardDefaults.outlinedElevation,
    border: BorderStroke = AppCardDefaults.outlinedCardBorder(),
    content: @Composable ColumnScope.() -> Unit
) {
    AppCard(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        content = content
    )
}

/**
 * Elevated variant of AppCard with higher elevation.
 */
@Composable
fun AppElevatedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    shape: Shape = AppCardDefaults.shape,
    colors: CardColors = AppCardDefaults.elevatedCardColors(),
    elevation: Dp = AppCardDefaults.elevatedElevation,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    AppCard(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        content = content
    )
}

/**
 * Card with custom padding applied to content.
 */
@Composable
fun AppPaddedCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    contentPadding: Dp = Dimens.paddingMedium,
    shape: Shape = AppCardDefaults.shape,
    colors: CardColors = AppCardDefaults.cardColors(),
    elevation: Dp = AppCardDefaults.defaultElevation,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    AppCard(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border
    ) {
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}
