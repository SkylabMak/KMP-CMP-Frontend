package th.skylabmek.kmp_frontend.uiComponents.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import th.skylabmek.kmp_frontend.core.common.UiState
import th.skylabmek.kmp_frontend.domain.model.profile.LifeStatus
import th.skylabmek.kmp_frontend.ui.dimens.Dimens
import th.skylabmek.kmp_frontend.uiComponents.layout.footer.AppFooter
import th.skylabmek.kmp_frontend.uiComponents.layout.header.AppHeader
import th.skylabmek.kmp_frontend.ui.navigation.NavItemIcon
import th.skylabmek.kmp_frontend.uiComponents.layout.drawer.AppDrawer

/**
 * A default, simplified version of the AppLayout with integrated Drawer.
 *
 * @param modifier Modifier for the layout.
 * @param navigationItems Navigation items to display in the header and drawer.
 * @param content The main content of the screen.
 */
@Composable
fun AppLayoutDefault(
    modifier: Modifier = Modifier,
    lifeStatusState: UiState<LifeStatus>,
    navigationItems: List<NavItemIcon?> = emptyList(),
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    AppDrawer(
        drawerState = drawerState,
        navigationItems = navigationItems
    ) {
        AppLayout(
            modifier = modifier,
            lifeStatusState = lifeStatusState,
            navigationItems = navigationItems,
            onMenuClick = {
                scope.launch { drawerState.open() }
            },
            content = content
        )
    }
}

/**
 * The full-featured AppLayout that provides a consistent structure for the application,
 * integrating the [AppHeader] and [AppFooter] around the main content.
 *
 * Uses dynamic height measurement to precisely calculate header spacing.
 *
 * @param modifier Modifier for the layout.
 * @param navigationItems List of items to display in the header navigation.
 * @param onThemeToggle Callback for toggling the application theme.
 * @param onMenuClick Callback for when the menu icon is clicked.
 * @param footer The footer component, defaults to [AppFooter].
 * @param content The main content of the screen.
 */
@Composable
fun AppLayout(
    modifier: Modifier = Modifier,
    lifeStatusState: UiState<LifeStatus>,
    navigationItems: List<NavItemIcon?> = emptyList(),
    onThemeToggle: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    footer: @Composable () -> Unit = { AppFooter() },
    content: @Composable (PaddingValues) -> Unit
) {
    var headerHeightPx by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val screenPadding = Dimens.screenPadding

    val headerHeightDp = with(density) { headerHeightPx.toDp() }
    val headerReserved = headerHeightDp + screenPadding

    Box(modifier = modifier.fillMaxSize()) {
//        println("AppLayout is run")

        // Background scrollable content with footer
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val screenHeight = maxHeight

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // headerReserved
                Spacer(modifier = Modifier.height(headerReserved))

                // Main content area - fixed height to prevent LazyColumn issues
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = screenHeight - headerReserved)
                ) {
                    content(PaddingValues(0.dp))
                }

                // Footer appears below the content
                footer()
            }
        }

        // Header participates in layout (measurable!)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(screenPadding)
                .onSizeChanged { size ->
                    headerHeightPx = size.height
//                    println("Header height px = $headerHeightPx")
                }
        ) {
            AppHeader(
                lifeStatusState = lifeStatusState,
                navigationItems = navigationItems,
                onThemeToggle = onThemeToggle,
                onMenuClick = onMenuClick
            )
        }
    }
}

