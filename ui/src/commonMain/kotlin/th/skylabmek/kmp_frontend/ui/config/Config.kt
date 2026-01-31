package th.skylabmek.kmp_frontend.ui.config

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import th.skylabmek.kmp_frontend.ui.dimens.AppDimens
import th.skylabmek.kmp_frontend.ui.dimens.CompactDimens
import th.skylabmek.kmp_frontend.ui.dimens.ExpandedDimens
import th.skylabmek.kmp_frontend.ui.dimens.LocalDimens
import th.skylabmek.kmp_frontend.ui.dimens.MediumDimens

enum class ThemeSetting {
    LIGHT, DARK, SYSTEM
}

data class UiConfig(
    val themeSetting: ThemeSetting = ThemeSetting.SYSTEM,
    val isDark: Boolean = false,
    val isDesktop: Boolean = false,
    val isTablet: Boolean = false,
    val screenWidth: Dp = 0.dp,
    val screenHeight: Dp = 0.dp,
    val dimens: AppDimens = CompactDimens
)

val LocalUiConfig = compositionLocalOf { UiConfig() }
val LocalThemeSetter = compositionLocalOf<(ThemeSetting) -> Unit> { {} }

object UiConfigDefaults {
    val DesktopThreshold = 800.dp
    val TabletThreshold = 600.dp
}

@Composable
fun ProvideUiConfig(
    maxWidth: Dp,
    maxHeight: Dp,
    themeSetting: ThemeSetting = ThemeSetting.SYSTEM,
    onThemeSettingChange: (ThemeSetting) -> Unit = {},
    content: @Composable () -> Unit
) {
    val isDesktop = maxWidth >= UiConfigDefaults.DesktopThreshold
    val isTablet = maxWidth >= UiConfigDefaults.TabletThreshold && maxWidth < UiConfigDefaults.DesktopThreshold
    
    val systemInDarkTheme = isSystemInDarkTheme()
    val isDark = when (themeSetting) {
        ThemeSetting.LIGHT -> false
        ThemeSetting.DARK -> true
        ThemeSetting.SYSTEM -> systemInDarkTheme
    }

    val dimens = when {
        isDesktop -> ExpandedDimens
        isTablet -> MediumDimens
        else -> CompactDimens
    }
    
    val config = UiConfig(
        themeSetting = themeSetting,
        isDark = isDark,
        isDesktop = isDesktop,
        isTablet = isTablet,
        screenWidth = maxWidth,
        screenHeight = maxHeight,
        dimens = dimens
    )

    CompositionLocalProvider(
        LocalUiConfig provides config,
        LocalDimens provides dimens,
        LocalThemeSetter provides onThemeSettingChange
    ) {
        content()
    }
}

/**
 * Global access to UI Configuration.
 * Usage: UI.isDark, UI.isDesktop, UI.dimens.paddingMedium
 */
val UI: UiConfig
    @Composable
    @ReadOnlyComposable
    get() = LocalUiConfig.current

/**
 * Global access to Theme Setter.
 * Usage: ThemeSetter.current(ThemeSetting.DARK)
 */
val ThemeSetter: (ThemeSetting) -> Unit
    @Composable
    @ReadOnlyComposable
    get() = LocalThemeSetter.current
