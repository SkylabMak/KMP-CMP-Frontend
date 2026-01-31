package th.skylabmek.kmp_frontend.features.profile.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.ktor.client.utils.EmptyContent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import th.skylabmek.kmp_frontend.core.common.UiState
import th.skylabmek.kmp_frontend.core.common.errorMessage
import th.skylabmek.kmp_frontend.domain.model.profile.Announce
import th.skylabmek.kmp_frontend.domain.model.profile.LifeStatus
import th.skylabmek.kmp_frontend.domain.model.profile.Performance
import th.skylabmek.kmp_frontend.features.profile.presentation.viewmodel.ProfileViewModel
import th.skylabmek.kmp_frontend.shared_resources.Res
import th.skylabmek.kmp_frontend.shared_resources.ic_profile
import th.skylabmek.kmp_frontend.shared_resources.announcements_header
import th.skylabmek.kmp_frontend.shared_resources.profile_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    profileId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(profileId) {
        viewModel.getOrLoadProfileBasicData(profileId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(stringResource(Res.string.profile_title)) })
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (val state = uiState) {
                is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is UiState.Error -> Text(
                    text = state.uiError.errorMessage()(), 
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
                is UiState.Success ->
                    EmptyContent
//                    ProfileContent(
//                    lifeStatus = state.data.lifeStatus,
//                    announces = state.data.announces,
//                    performances = state
//                )
            }
        }
    }
}

@Composable
fun ProfileContent(
    lifeStatus: LifeStatus,
    announces: List<Announce>,
    performances: List<Performance>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            LifeStatusCard(lifeStatus)
        }
        
        if (announces.isNotEmpty()) {
            item {
                Text(stringResource(Res.string.announcements_header), style = MaterialTheme.typography.titleLarge)
            }
            items(announces) { announce ->
                AnnounceCard(announce)
            }
        }

        if (performances.isNotEmpty()) {
            item {
//                PerformanceSection(performances = performances)
            }
        }
    }
}

@Composable
public fun LifeStatusCard(status: LifeStatus) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray).padding(8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(parseColor(status.colorToken))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(status.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                status.description?.let {
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun AnnounceCard(announce: Announce) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    announce.announceType.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Text(announce.createdAt, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
            
            announce.title?.let {
                Text(it, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
            }
            
            announce.message?.let {
                Text(it, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 4.dp))
            }

            if (announce.linkUrl != null) {
                TextButton(
                    onClick = { /* Handle Link */ },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(announce.linkText ?: "Read More")
                }
            }
        }
    }
}

fun parseColor(colorString: String): Color {
    return try {
        Color(colorString.removePrefix("#").toLong(16) or 0xFF000000)
    } catch (e: Exception) {
        Color.Gray
    }
}
