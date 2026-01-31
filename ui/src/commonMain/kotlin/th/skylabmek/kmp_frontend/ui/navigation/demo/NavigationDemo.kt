package th.skylabmek.kmp_frontend.ui.navigation.demo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.rememberNavBackStack
import th.skylabmek.kmp_frontend.navigation.presentation.AppNavHost
import th.skylabmek.kmp_frontend.navigation.tools.ProvideNavigator
import th.skylabmek.kmp_frontend.navigation.model.createNavigationConfig
import th.skylabmek.kmp_frontend.navigation.tools.NavigatorAccessor
import th.skylabmek.kmp_frontend.ui.theme.AppMaterialTheme

/**
 * you can example with
 * NavigationDemo(
 *             onDeepLinkHandled = { handle ->
 *                 LaunchedEffect(deepLinkUri) {
 *                     deepLinkUri?.let {
 *                         handle(it)
 *                         onDeepLinkConsumed()
 *                     }
 *                 }
 *             }
 *         )
 */
@Composable
fun NavigationDemo(
    onDeepLinkHandled: @Composable (handle: (String) -> Unit) -> Unit = {}
) {
    // 1. Setup providers
    val providers = remember { listOf(FeatureANavProvider()) }

    // 2. Setup config using the shared utility function
    val navConfig = remember { createNavigationConfig(providers) }

    // 3. Setup backstack using the config
    val backStack = rememberNavBackStack(navConfig, FeatureANavKey.Page1)

    // 4. Wrap in Theme, Provider and Host
    AppMaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProvideNavigator(backStack = backStack, providers = providers) {
                val navigator = NavigatorAccessor.current
                onDeepLinkHandled { uri ->
                    navigator.handleDeepLink(uri)
                }

                AppNavHost(
                    backStack = backStack,
                    onBack = { if (backStack.size > 1) backStack.removeAt(backStack.size - 1) },
                    providers = providers
                )
            }
        }
    }
}

@Preview
@Composable
fun NavigationDemoPreview(){
    NavigationDemo()
}