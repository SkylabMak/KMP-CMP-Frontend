package th.skylabmek.kmp_frontend.uiComponents.layout.header

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import th.skylabmek.kmp_frontend.core.common.UiState
import th.skylabmek.kmp_frontend.ui.common.ComponentPreviewWrapper
import th.skylabmek.kmp_frontend.ui.common.DevicePreviews
import th.skylabmek.kmp_frontend.ui.config.ThemeSetting
import th.skylabmek.kmp_frontend.ui.navigation.NavItemIcon

@DevicePreviews
@Composable
fun AppHeaderPreview() {
    var themeSetting by remember { mutableStateOf(ThemeSetting.SYSTEM) }
    
    val navItems = listOf(
        NavItemIcon(title = "Home"),
        null, // Blurred placeholder
        NavItemIcon(title = "Projects")
    )

    ComponentPreviewWrapper {
        AppHeader(
            navigationItems = navItems,
            themeSetting = themeSetting,
            onThemeToggle = {
                themeSetting = when (themeSetting) {
                    ThemeSetting.SYSTEM -> ThemeSetting.LIGHT
                    ThemeSetting.LIGHT -> ThemeSetting.DARK
                    ThemeSetting.DARK -> ThemeSetting.SYSTEM
                }
            },
            onMenuClick = { println("Menu clicked") },
            lifeStatusState = UiState.Loading
        )
    }
}

@Composable
fun AppHeaderThemePreview(setting: ThemeSetting) {
    val navItems = listOf(
        NavItemIcon(title = "Home"),
        null,
        NavItemIcon(title = "About")
    )
    
    ComponentPreviewWrapper {
        AppHeader(
            navigationItems = navItems,
            themeSetting = setting,
            onThemeToggle = { },
            onMenuClick = { },
            lifeStatusState = UiState.Loading
        )
    }
}

@DevicePreviews
@Composable
fun AppHeaderDarkThemePreview() {
    AppHeaderThemePreview(setting = ThemeSetting.DARK)
}

@DevicePreviews
@Composable
fun AppHeaderLightThemePreview() {
    AppHeaderThemePreview(setting = ThemeSetting.LIGHT)
}

@DevicePreviews
@Composable
fun AppHeaderSystemThemePreview() {
    AppHeaderThemePreview(setting = ThemeSetting.SYSTEM)
}
