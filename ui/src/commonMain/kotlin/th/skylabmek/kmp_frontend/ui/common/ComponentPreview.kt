package th.skylabmek.kmp_frontend.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import th.skylabmek.kmp_frontend.ui.config.ProvideUiConfig
import th.skylabmek.kmp_frontend.ui.config.ThemeSetting
import th.skylabmek.kmp_frontend.ui.theme.AppMaterialTheme

/**
 * Multi-preview annotation for different screen sizes.
 * Height is doubled to accommodate both Light and Dark versions.
 */
@Preview(name = "Mobile", group = "Devices", widthDp = 360, heightDp = 1280, showBackground = true)
@Preview(name = "Tablet", group = "Devices", widthDp = 768, heightDp = 1600, showBackground = true)
@Preview(name = "Desktop", group = "Devices", widthDp = 1280, heightDp = 1600, showBackground = true)
annotation class DevicePreviews

/**
 * A wrapper for components in previews that automatically provides the UI configuration
 * and Theme. It renders the component twice: once in Light mode and once in Dark mode.
 */
@Composable
fun ComponentPreviewWrapper(
    content: @Composable () -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        // Explicitly capture values from BoxWithConstraintsScope to avoid implicit receiver issues
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val halfHeight = screenHeight / 2
        
        Column(modifier = Modifier.fillMaxSize()) {
            // Light Version
            Box(modifier = Modifier.fillMaxWidth().height(halfHeight)) {
                ProvideUiConfig(
                    maxWidth = screenWidth,
                    maxHeight = halfHeight,
                    themeSetting = ThemeSetting.LIGHT
                ) {
                    AppMaterialTheme {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            content()
                        }
                    }
                }
            }
            
            // Dark Version
            Box(modifier = Modifier.fillMaxWidth().height(halfHeight)) {
                ProvideUiConfig(
                    maxWidth = screenWidth,
                    maxHeight = halfHeight,
                    themeSetting = ThemeSetting.DARK
                ) {
                    AppMaterialTheme {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            content()
                        }
                    }
                }
            }
        }
    }
}
