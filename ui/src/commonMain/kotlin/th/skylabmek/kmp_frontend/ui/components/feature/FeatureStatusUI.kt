package th.skylabmek.kmp_frontend.ui.components.feature

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import th.skylabmek.kmp_frontend.domain.model.feature.FeatureStatusCode
import th.skylabmek.kmp_frontend.ui.components.card.appCard.AppCard

@Composable
fun FeatureStatusStripped(
    statusCode: FeatureStatusCode,
    modifier: Modifier = Modifier
) {
    val statusColor = when (statusCode) {
        FeatureStatusCode.OPERATIONAL -> Color(0xFF4CAF50) // Green
        FeatureStatusCode.UNDER_CONSTRUCTION -> Color(0xFFFF9800) // Orange
        FeatureStatusCode.MAINTENANCE -> Color(0xFF2196F3) // Blue
        FeatureStatusCode.DOWN -> Color(0xFFF44336) // Red
        FeatureStatusCode.CLOSED -> Color(0xFF9E9E9E) // Grey
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Status Indicator Dot
            Surface(
                modifier = Modifier.size(8.dp),
                shape = MaterialTheme.shapes.small,
                color = statusColor
            ) {}

            Text(
                text = stringResource(statusCode.getDisplayNameRes()),
                style = MaterialTheme.typography.titleSmall,
                color = statusColor
            )
        }

        Text(
            text = stringResource(statusCode.getDescriptionRes()),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        statusCode.getInstructionRes()?.let { instructionRes ->
            Text(
                text = stringResource(instructionRes),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
fun FeatureStatusCard(
    statusCode: FeatureStatusCode,
    modifier: Modifier = Modifier
) {
    AppCard(
        modifier = modifier.fillMaxWidth()
    ) {
        FeatureStatusStripped(
            statusCode = statusCode,
            modifier = Modifier.padding(16.dp)
        )
    }
}
