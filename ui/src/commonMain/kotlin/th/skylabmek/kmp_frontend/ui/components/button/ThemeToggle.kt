package th.skylabmek.kmp_frontend.ui.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import th.skylabmek.kmp_frontend.shared_resources.Res
import th.skylabmek.kmp_frontend.shared_resources.header_theme_toggle_desc
import th.skylabmek.kmp_frontend.ui.config.ThemeSetting
import th.skylabmek.kmp_frontend.ui.config.ThemeSetter
import th.skylabmek.kmp_frontend.ui.config.UI

@Composable
fun ThemeToggle(
    modifier: Modifier = Modifier
) {
    val themeSetting = UI.themeSetting
    val setTheme = ThemeSetter

    ThemeToggleButton(
        themeSetting = themeSetting,
        onToggle = {
            val nextSetting = when (themeSetting) {
                ThemeSetting.LIGHT -> ThemeSetting.DARK
                ThemeSetting.DARK -> ThemeSetting.SYSTEM
                ThemeSetting.SYSTEM -> ThemeSetting.LIGHT
            }
            setTheme(nextSetting)
        },
        modifier = modifier
    )
}

@Composable
public fun ThemeToggleButton(
    themeSetting: ThemeSetting,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = when (themeSetting) {
        ThemeSetting.LIGHT -> Icons.Default.LightMode
        ThemeSetting.DARK -> Icons.Default.DarkMode
        ThemeSetting.SYSTEM -> Icons.Default.BrightnessAuto
    }

    IconButton(
        onClick = onToggle,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(Res.string.header_theme_toggle_desc),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}
