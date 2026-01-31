package th.skylabmek.kmp_frontend.features.home.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import th.skylabmek.kmp_frontend.core.common.UiState
import th.skylabmek.kmp_frontend.features.app.presentation.viewmodel.AppViewModel
import th.skylabmek.kmp_frontend.features.home.presentation.ui.performance.PerformancePreviewDemo
import th.skylabmek.kmp_frontend.features.home.presentation.ui.performance.PerformancePreviewSection
import th.skylabmek.kmp_frontend.features.profile.presentation.viewmodel.ProfileBasicData
import th.skylabmek.kmp_frontend.features.profile.presentation.viewmodel.ProfileViewModel
import th.skylabmek.kmp_frontend.ui.config.UI
import th.skylabmek.kmp_frontend.ui.dimens.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    appViewModel: AppViewModel = koinViewModel(),
    profileViewModel: ProfileViewModel = koinViewModel(),
    profileId: String,
    appId: String,
) {
    val profileUiState by profileViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        appViewModel.getOrLoadFeatureStatus(appId)
        profileViewModel.getOrLoadProfileBasicData(profileId)
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isDesktop = UI.isDesktop

            Column(
                modifier = Modifier
                    .padding(Dimens.screenPadding)
            ) {
                if (isDesktop) {
                    DesktopLayout(
                        PaddingValues(Dimens.screenPadding),
                        profileUiState,
                        appViewModel,
                        profileViewModel,
                        appId,
                        profileId
                    )
                } else {
                    MobileLayout(
                        PaddingValues(Dimens.screenPadding),
                        profileUiState,
                        appViewModel,
                        profileViewModel,
                        appId,
                        profileId
                    )
                }
            }
        }
}

@Composable
fun MobileLayout(
    padding: PaddingValues,
    profileUiState: UiState<ProfileBasicData>,
    appViewModel: AppViewModel,
    profileViewModel: ProfileViewModel,
    appId: String,
    profileId: String
) {
    Column(
    ) {
        PerformancePreviewDemo(
            appViewModel = appViewModel,
            profileViewModel = profileViewModel,
            appId = appId,
            profileId = profileId
        )


        PerformancePreviewSection(
            appViewModel = appViewModel,
            profileViewModel = profileViewModel,
            appId = appId,
            profileId = profileId
        )


    }
}

@Composable
fun DesktopLayout(
    padding: PaddingValues,
    profileUiState: UiState<ProfileBasicData>,
    appViewModel: AppViewModel,
    profileViewModel: ProfileViewModel,
    appId: String,
    profileId: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PerformancePreviewDemo(
            appViewModel = appViewModel,
            profileViewModel = profileViewModel,
            appId = appId,
            profileId = profileId
        )

        PerformancePreviewSection(
            appViewModel = appViewModel,
            profileViewModel = profileViewModel,
            appId = appId,
            profileId = profileId
        )
    }
}
