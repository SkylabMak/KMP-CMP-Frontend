package th.skylabmek.kmp_frontend.navigation.model

import th.skylabmek.kmp_frontend.navigation.tools.NavKey

/**
 * Represents a navigation item in the application layout.
 */
interface NavItem {
    val title: String
    val key: NavKey?
}

/**
 * Basic implementation of [NavItem].
 */
data class NavItemData(
    override val title: String,
    override val key: NavKey? = null
) : NavItem
