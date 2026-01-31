package th.skylabmek.kmp_frontend.uiComponents.layout.footer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import th.skylabmek.kmp_frontend.ui.common.ComponentPreviewWrapper
import th.skylabmek.kmp_frontend.ui.common.DevicePreviews
import th.skylabmek.kmp_frontend.ui.theme.AppMaterialTheme

@DevicePreviews
@Composable
fun AppFooterMultiPreview() {
    ComponentPreviewWrapper {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Spacer to show footer at bottom
            Spacer(modifier = Modifier.weight(1f))

            AppFooter()
        }
    }
}

// Component Previews
@Preview
@Composable
fun CopyrightSectionPreview() {
    AppMaterialTheme {
        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            CopyrightSection(currentYear = 2026)
        }
    }
}

@Preview
@Composable
fun ConstructionStatusPreview() {
    AppMaterialTheme {
        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            ConstructionStatus()
        }
    }
}

@Preview
@Composable
fun FooterLinksPreview() {
    AppMaterialTheme {
        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            FooterLinks(tempWebsiteUrl = "https://temp.skylabmek.th")
        }
    }
}

@Preview
@Composable
fun TechStackSectionPreview() {
    AppMaterialTheme {
        Surface(
            modifier = Modifier.padding(16.dp)
        ) {
            TechStackSection()
        }
    }
}
