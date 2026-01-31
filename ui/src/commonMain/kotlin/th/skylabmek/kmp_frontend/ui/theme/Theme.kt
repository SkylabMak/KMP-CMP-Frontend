package th.skylabmek.kmp_frontend.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import th.skylabmek.kmp_frontend.ui.color.DarkColorScheme
import th.skylabmek.kmp_frontend.ui.color.LightColorScheme
import th.skylabmek.kmp_frontend.ui.config.UI

@Composable
fun AppMaterialTheme(
    content: @Composable () -> Unit
) {
    // Use the global UI config which already calculates isDark based on ThemeSetting
    val colorScheme = if (UI.isDark) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}

@Composable
fun App() {
    AppMaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text("Hello Compose Multiplatform!")
        }
    }
}
