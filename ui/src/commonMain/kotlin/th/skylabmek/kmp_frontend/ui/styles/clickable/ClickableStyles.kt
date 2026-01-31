package th.skylabmek.kmp_frontend.ui.styles.clickable

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

/**
 * Standard clickable with scale animation on press
 */
@Composable
fun Modifier.clickableWithScale(
    enabled: Boolean = true,
    scaleOnPress: Float = 0.95f,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleOnPress else 1f,
        label = "scale"
    )

    return this
        .scale(scale)
        .clickable(
            interactionSource = interactionSource,
            indication = null, // Remove ripple if you want only scale
            enabled = enabled,
            onClick = onClick
        )
}

/**
 * Clickable with hover effect (for desktop/web)
 */
@Composable
fun Modifier.clickableWithHover(
    enabled: Boolean = true,
    hoverAlpha: Float = 0.7f,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val alpha by animateFloatAsState(
        targetValue = if (isHovered) hoverAlpha else 1f,
        label = "alpha"
    )

    return this
        .graphicsLayer { this.alpha = alpha }
        .hoverable(interactionSource = interactionSource)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = onClick
        )
}

/**
 * Clickable with both hover and press effects
 */
@Composable
fun Modifier.clickableWithEffects(
    enabled: Boolean = true,
    scaleOnPress: Float = 0.95f,
    hoverAlpha: Float = 0.8f,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleOnPress else 1f,
        label = "scale"
    )

    val alpha by animateFloatAsState(
        targetValue = when {
            isPressed -> 0.9f
            isHovered -> hoverAlpha
            else -> 1f
        },
        label = "alpha"
    )

    return this
        .scale(scale)
        .graphicsLayer { this.alpha = alpha }
        .hoverable(interactionSource = interactionSource)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = onClick
        )
}

/**
 * Bounce effect on press
 */
@Composable
fun Modifier.clickableWithBounce(
    enabled: Boolean = true,
    bounceScale: Float = 1.1f,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) bounceScale else 1f,
        label = "bounce"
    )

    return this
        .scale(scale)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = onClick
        )
}

/**
 * Pulse effect (scale up slightly on hover/press)
 */
@Composable
fun Modifier.clickableWithPulse(
    enabled: Boolean = true,
    pulseScale: Float = 1.05f,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = when {
            isPressed -> pulseScale * 0.95f // Slightly smaller when pressed
            isHovered -> pulseScale
            else -> 1f
        },
        label = "pulse"
    )

    return this
        .scale(scale)
        .hoverable(interactionSource = interactionSource)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = onClick
        )
}

/**
 * Custom tap feedback with manual state control
 */
@Composable
fun Modifier.clickableWithFeedback(
    enabled: Boolean = true,
    scaleOnPress: Float = 0.95f,
    onClick: () -> Unit
): Modifier {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) scaleOnPress else 1f,
        label = "feedback_scale"
    )

    return this
        .scale(scale)
        .pointerInput(enabled) {
            if (enabled) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    },
                    onTap = { onClick() }
                )
            }
        }
}

@Composable
fun Modifier.clickableWithPulseRipple(
    enabled: Boolean = true,
    pulseScale: Float = 1.05f,
    rippleColor: Color = Color.White.copy(alpha = 0.3f),
    rippleShape: Shape = CircleShape,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    var showRipple by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = when {
            isPressed -> pulseScale * 0.95f
            isHovered -> pulseScale
            else -> 1f
        },
        label = "pulse"
    )

    // Ripple animation
    val rippleScale by animateFloatAsState(
        targetValue = if (showRipple) 1.5f else 0f,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = "ripple_scale",
        finishedListener = { showRipple = false }
    )

    val rippleAlpha by animateFloatAsState(
        targetValue = if (showRipple) 0f else 1f,
        animationSpec = tween(durationMillis = 600, easing = LinearEasing),
        label = "ripple_alpha"
    )

    return this.then(
        Modifier
            .scale(scale)
            .hoverable(interactionSource = interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = {
                    showRipple = true
                    onClick()
                }
            )
            .graphicsLayer {
                // Draw ripple behind content
                if (showRipple) {
                    this.scaleX = rippleScale
                    this.scaleY = rippleScale
                    this.alpha = rippleAlpha
                }
            }
    )
}

/**
 * Advanced pulse ripple with customizable behavior
 */
@Composable
fun Modifier.clickableWithAdvancedRipple(
    enabled: Boolean = true,
    pulseScale: Float = 1.05f,
    rippleDuration: Int = 600,
    rippleMaxScale: Float = 1.5f,
    onClick: () -> Unit
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    var rippleCount by remember { mutableStateOf(0) }

    val scale by animateFloatAsState(
        targetValue = when {
            isPressed -> pulseScale * 0.95f
            isHovered -> pulseScale
            else -> 1f
        },
        label = "pulse"
    )

    LaunchedEffect(rippleCount) {
        if (rippleCount > 0) {
            kotlinx.coroutines.delay(rippleDuration.toLong())
        }
    }

    return this
        .scale(scale)
        .hoverable(interactionSource = interactionSource)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = {
                rippleCount++
                onClick()
            }
        )
}