package th.skylabmek.kmp_frontend.features.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import th.skylabmek.kmp_frontend.core.common.UiError
import th.skylabmek.kmp_frontend.core.common.UiState
import th.skylabmek.kmp_frontend.core.network.result.NetworkResult
import th.skylabmek.kmp_frontend.domain.model.profile.Announce
import th.skylabmek.kmp_frontend.domain.model.profile.LifeStatus
import th.skylabmek.kmp_frontend.domain.model.profile.Performance
import th.skylabmek.kmp_frontend.domain.repository.profile.ProfileRepository

data class ProfileBasicData(
    val lifeStatus: LifeStatus,
    val announces: List<Announce>
)

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private var hasBasicDataLoadedOnce = false
    private var hasPerformancesLoadedOnce = false

    private val _uiState = MutableStateFlow<UiState<ProfileBasicData>>(UiState.Loading)
    val uiState: StateFlow<UiState<ProfileBasicData>> = _uiState.asStateFlow()

    private val _performancesState = MutableStateFlow<UiState<List<Performance>>>(UiState.Loading)
    val performancesState: StateFlow<UiState<List<Performance>>> = _performancesState.asStateFlow()

    fun getOrLoadProfileBasicData(profileId: String): StateFlow<UiState<ProfileBasicData>> {
        if (!hasBasicDataLoadedOnce) {
            hasBasicDataLoadedOnce = true
            loadProfileBasicData(profileId)
        }
        return uiState
    }

    fun getOrLoadPerformances(profileId: String): StateFlow<UiState<List<Performance>>> {
        if (!hasPerformancesLoadedOnce) {
            hasPerformancesLoadedOnce = true
            loadPerformances(profileId)
        }
        return performancesState
    }

    fun loadProfileBasicData(profileId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            
            val lifeStatusResult = profileRepository.getLifeStatus(profileId)
            val announcesResult = profileRepository.getAnnounces(profileId)

            if (lifeStatusResult is NetworkResult.Success && 
                announcesResult is NetworkResult.Success) {
                _uiState.value = UiState.Success(
                    ProfileBasicData(
                        lifeStatus = lifeStatusResult.data,
                        announces = announcesResult.data.items
                    )
                )
            } else {
                val networkError = when {
                    lifeStatusResult is NetworkResult.Failure -> lifeStatusResult.error
                    announcesResult is NetworkResult.Failure -> announcesResult.error
                    else -> th.skylabmek.kmp_frontend.core.network.result.NetworkError.Unknown(Throwable("Unknown error"))
                }
                _uiState.value = UiState.Error(
                    UiError.StringRes(networkError.asStringResource())
                )
            }
        }
    }

    fun loadPerformances(profileId: String) {
        viewModelScope.launch {
            _performancesState.value = UiState.Loading
            
            when (val result = profileRepository.getPerformances(profileId)) {
                is NetworkResult.Success -> {
                    _performancesState.value = UiState.Success(result.data)
                }
                is NetworkResult.Failure -> {
                    _performancesState.value = UiState.Error(
                        UiError.StringRes(result.error.asStringResource())
                    )
                }
            }
        }
    }
}
