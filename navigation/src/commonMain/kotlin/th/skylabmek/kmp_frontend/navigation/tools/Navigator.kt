package th.skylabmek.kmp_frontend.navigation.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner

/**
 * Global navigator to control Navigation 3 backstack.
 */
class Navigator(
    private val backStack: NavBackStack<NavKey>,
    private val providers: List<FeatureNavProvider>
) {
    /**
     * Navigate to a new route by pushing it onto the stack.
     */
    fun navigate(key: NavKey) {
        backStack.add(key)
    }

    /**
     * Go back to the previous screen.
     */
    fun back() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.size - 1)
        }
    }

    /**
     * Clear the stack and set a new root.
     */
    fun setRoot(key: NavKey) {
        backStack.clear()
        backStack.add(key)
    }

    /**
     * Check if can go back.
     */
    fun canGoBack(): Boolean = backStack.size > 1

    /**
     * Handles a deep link by mapping it to a NavKey and navigating to it.
     */
    fun handleDeepLink(uri: String) {
        providers.forEach { provider ->
            provider.mapUriToNavKey(uri)?.let { key ->
                // For deep links, we might want to clear the stack or push. 
                // Usually, we push or set as root depending on app requirements.
                // Here we'll just navigate to it.
                navigate(key)
                return
            }
        }
    }
}

val LocalNavigator = compositionLocalOf<Navigator> {
    error("Navigator not provided! Make sure to wrap your app in ProvideNavigator.")
}

/**
 * Global accessor for the navigator.
 * Usage: Navigator.navigate(MyRoute)
 */
object NavigatorAccessor {
    val current: Navigator
        @Composable
        @ReadOnlyComposable
        get() = LocalNavigator.current
}

/**
 * Provider for the global navigator.
 */
@Composable
fun ProvideNavigator(
    backStack: NavBackStack<NavKey>,
    providers: List<FeatureNavProvider>,
    content: @Composable (() -> Unit)
) {
    val navigator = remember(backStack, providers) { Navigator(backStack, providers) }
    
    // Create a dummy dispatcher owner for environments that don't provide one (like Previews)
    val dummyDispatcherOwner = remember {
        object : NavigationEventDispatcherOwner {
            override val navigationEventDispatcher = NavigationEventDispatcher()
        }
    }

    CompositionLocalProvider(
        LocalNavigator provides navigator,
        // Provide a dummy dispatcher owner to satisfy Navigation 3 internal back handler requirements in Previews
        LocalNavigationEventDispatcherOwner provides dummyDispatcherOwner
    ) {
        content()
    }
}
