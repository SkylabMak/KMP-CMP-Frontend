package th.skylabmek.kmp_frontend.presentation.ui

import androidx.compose.runtime.Composable
import th.skylabmek.kmp_frontend.core.common.AppConfig
import th.skylabmek.kmp_frontend.navigation.rememberAppNavigationState
import th.skylabmek.kmp_frontend.uiComponents.layout.MainContent

@Composable
fun App(
    deepLinkUri: String? = null,
    onDeepLinkConsumed: () -> Unit = {}
) {
    val navigationState = rememberAppNavigationState(
        profileId = AppConfig.PROFILE_ID,
        appId = AppConfig.APP_ID
    )

    MainContent(
        profileId = AppConfig.PROFILE_ID,
        backStack = navigationState.backStack,
        providers = navigationState.providers,
        navigationItems = navigationState.navItems,
        deepLinkUri = deepLinkUri,
        onDeepLinkConsumed = onDeepLinkConsumed
    )
}
