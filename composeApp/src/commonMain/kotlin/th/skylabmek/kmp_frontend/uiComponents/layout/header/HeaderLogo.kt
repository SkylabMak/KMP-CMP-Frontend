package th.skylabmek.kmp_frontend.uiComponents.layout.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import th.skylabmek.kmp_frontend.shared_resources.Res
import th.skylabmek.kmp_frontend.shared_resources.mek_lowercase

@Composable
fun HeaderLogo(
    size: Dp = 40.dp,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.size(size),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer // Optional: background for transparent images
    ) {
        Image(
            painter = painterResource(Res.drawable.mek_lowercase),
            contentDescription = null, // decorative
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(MaterialTheme.shapes.medium)
        )
    }
}
