package th.skylabmek.kmp_frontend.ui.components.feature

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import th.skylabmek.kmp_frontend.domain.model.feature.AppFeatureStatus
import th.skylabmek.kmp_frontend.domain.model.feature.FeatureStatusCode

/**
 * A wrapper component that conditionally displays content based on the feature's status.
 * If the feature is closed or not operational, it displays a [FeatureStatusCard] instead.
 */
@Composable
fun FeatureGate(
    featureStatus: AppFeatureStatus,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    if (featureStatus.isClosed || featureStatus.statusCode != FeatureStatusCode.OPERATIONAL) {
        FeatureStatusCard(
            statusCode = if (featureStatus.isClosed) FeatureStatusCode.CLOSED else featureStatus.statusCode,
            modifier = modifier
        )
    } else {
        content()
    }
}
