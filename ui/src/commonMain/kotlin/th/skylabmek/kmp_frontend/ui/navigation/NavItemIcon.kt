package th.skylabmek.kmp_frontend.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import th.skylabmek.kmp_frontend.navigation.model.NavItem
import th.skylabmek.kmp_frontend.navigation.tools.NavKey

/**
 * UI-specific navigation item that includes an icon.
 */
data class NavItemIcon(
    override val title: String,
    override val key: NavKey? = null,
    val icon: ImageVector? = null,
    val secondaryIcon: ImageVector? = null
) : NavItem