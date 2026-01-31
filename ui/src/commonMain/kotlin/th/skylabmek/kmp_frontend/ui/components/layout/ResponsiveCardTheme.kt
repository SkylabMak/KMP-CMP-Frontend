package th.skylabmek.kmp_frontend.ui.components.layout

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import th.skylabmek.kmp_frontend.ui.components.card.appCard.AppCard
import th.skylabmek.kmp_frontend.ui.components.card.appCard.AppElevatedCard
import th.skylabmek.kmp_frontend.ui.components.card.appCard.AppOutlinedCard
import th.skylabmek.kmp_frontend.ui.components.card.appCard.AppPaddedCard
import th.skylabmek.kmp_frontend.ui.config.UI
import th.skylabmek.kmp_frontend.ui.dimens.Dimens

/**
 * Types of cards available for ResponsiveCardTheme.
 */
enum class ResponsiveCardType {
    Standard,
    Outlined,
    Elevated,
    Padded
}

/**
 * A responsive container that wraps content in a specified AppCard type.
 * On Desktop, it applies a max-width constraint defined in Dimens.
 */
@Composable
fun ResponsiveCardTheme(
    modifier: Modifier = Modifier,
    cardType: ResponsiveCardType = ResponsiveCardType.Standard,
    content: @Composable ColumnScope.() -> Unit
) {
    val uiConfig = UI
    
    val responsiveModifier = modifier
        .fillMaxWidth()
        .let {
            if (uiConfig.isDesktop) it.widthIn(max = Dimens.containerMaxWidth)
            else it
        }

    when (cardType) {
        ResponsiveCardType.Standard -> {
            AppCard(
                modifier = responsiveModifier,
                content = content
            )
        }
        ResponsiveCardType.Outlined -> {
            AppOutlinedCard(
                modifier = responsiveModifier,
                content = content
            )
        }
        ResponsiveCardType.Elevated -> {
            AppElevatedCard(
                modifier = responsiveModifier,
                content = content
            )
        }
        ResponsiveCardType.Padded -> {
            AppPaddedCard(
                modifier = responsiveModifier,
                content = content
            )
        }
    }
}
