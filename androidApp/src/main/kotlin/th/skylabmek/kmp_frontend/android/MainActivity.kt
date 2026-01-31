package th.skylabmek.kmp_frontend.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import th.skylabmek.kmp_frontend.presentation.ui.App

class MainActivity : ComponentActivity() {
    private val deepLinkUri = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        handleIntent(intent)

        setContent {
            App(deepLinkUri = deepLinkUri.value, onDeepLinkConsumed = { deepLinkUri.value = null })
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.data?.toString()?.let { uri ->
            deepLinkUri.value = uri
        }
    }
}
