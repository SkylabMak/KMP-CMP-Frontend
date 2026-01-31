package th.skylabmek.kmp_frontend.uiComponents.layout.footer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import th.skylabmek.kmp_frontend.shared_resources.Res
import th.skylabmek.kmp_frontend.shared_resources.footer_copyright
import th.skylabmek.kmp_frontend.shared_resources.footer_construction_status
import th.skylabmek.kmp_frontend.shared_resources.footer_construction_icon_desc
import th.skylabmek.kmp_frontend.shared_resources.footer_tech_stack
import th.skylabmek.kmp_frontend.shared_resources.footer_temp_info
import th.skylabmek.kmp_frontend.shared_resources.footer_visit_temp_site
import th.skylabmek.kmp_frontend.shared_resources.url_temp_portfolio
import th.skylabmek.kmp_frontend.ui.config.UI
import kotlin.time.Clock

@Composable
fun AppFooter(
    modifier: Modifier = Modifier,
    tempWebsiteUrl: String = stringResource(Res.string.url_temp_portfolio)
) {
    val uiConfig = UI
    val currentYear = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .year

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 2.dp
    ) {
        if (uiConfig.isDesktop) {
            DesktopFooterLayout(
                currentYear = currentYear,
                tempWebsiteUrl = tempWebsiteUrl
            )
        } else {
            MobileFooterLayout(
                currentYear = currentYear,
                tempWebsiteUrl = tempWebsiteUrl
            )
        }
    }
}

@Composable
private fun DesktopFooterLayout(
    currentYear: Int,
    tempWebsiteUrl: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CopyrightSection(currentYear = currentYear)

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ConstructionStatus()
                FooterLinks(tempWebsiteUrl = tempWebsiteUrl)
            }
        }

        TechStackSection()
    }
}

@Composable
private fun MobileFooterLayout(
    currentYear: Int,
    tempWebsiteUrl: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ConstructionStatus()
        FooterLinks(tempWebsiteUrl = tempWebsiteUrl)
        TechStackSection(isMobile = true)
        CopyrightSection(currentYear = currentYear, isCentered = true)
    }
}

@Composable
fun CopyrightSection(
    currentYear: Int,
    isCentered: Boolean = false,
    modifier: Modifier = Modifier
) {
    val copyrightText = stringResource(Res.string.footer_copyright, currentYear)

    Text(
        text = copyrightText,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = if (isCentered) TextAlign.Center else TextAlign.Start,
        modifier = modifier
    )
}

@Composable
fun ConstructionStatus(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Build,
            contentDescription = stringResource(Res.string.footer_construction_icon_desc),
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(Res.string.footer_construction_status),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun FooterLinks(
    tempWebsiteUrl: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.footer_temp_info),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        OutlinedButton(
            onClick = { uriHandler.openUri(tempWebsiteUrl) }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(Res.string.footer_visit_temp_site))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun TechStackSection(
    isMobile: Boolean = false,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(Res.string.footer_tech_stack),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
        textAlign = if (isMobile) TextAlign.Center else TextAlign.Start,
        modifier = modifier
    )
}
