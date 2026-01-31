package th.skylabmek.kmp_frontend

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.ComposeUIViewController
import th.skylabmek.kmp_frontend.presentation.ui.App

private val deepLinkUri = mutableStateOf<String?>(null)

fun MainViewController() = ComposeUIViewController {
    App(
        deepLinkUri = deepLinkUri.value,
        onDeepLinkConsumed = { deepLinkUri.value = null }
    )
}

fun onOpenUrl(url: String) {
    deepLinkUri.value = url
}
