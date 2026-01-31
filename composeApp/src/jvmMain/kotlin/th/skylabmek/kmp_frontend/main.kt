package th.skylabmek.kmp_frontend

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import th.skylabmek.kmp_frontend.di.initKoin
import th.skylabmek.kmp_frontend.presentation.ui.App

fun main(args: Array<String>) {
    initKoin()
    
    // Simple deep link handling via command line arguments for Desktop
    var deepLinkUri by mutableStateOf(args.firstOrNull { it.startsWith("http") })

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "kmp-frontend",
        ) {
            App(
                deepLinkUri = deepLinkUri,
                onDeepLinkConsumed = { deepLinkUri = null }
            )
        }
    }
}
