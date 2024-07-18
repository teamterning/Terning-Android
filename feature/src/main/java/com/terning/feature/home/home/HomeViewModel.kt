package com.terning.feature.home.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.request.ChangeFilteringRequestModel
import com.terning.domain.entity.request.ScrapRequestModel
import com.terning.domain.entity.response.HomeFilteringInfoModel
import com.terning.domain.entity.response.HomeRecommendInternModel
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.domain.repository.HomeRepository
import com.terning.domain.repository.MyPageRepository
import com.terning.domain.repository.ScrapRepository
import com.terning.feature.R
import com.terning.feature.home.home.model.HomeScrapViewState
import com.terning.feature.home.home.model.SortBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val myPageRepository: MyPageRepository,
    private val scrapRepository: ScrapRepository,
) : ViewModel() {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

    private val _homeSideEffect = MutableSharedFlow<HomeSideEffect>()
    val homeSideEffect get() = _homeSideEffect.asSharedFlow()

    private val _homeTodayState =
        MutableStateFlow<UiState<List<HomeTodayInternModel>>>(UiState.Loading)
    val homeTodayState get() = _homeTodayState.asStateFlow()

    private val _homeRecommendInternState =
        MutableStateFlow<UiState<List<HomeRecommendInternModel>>>(UiState.Loading)
    val homeRecommendInternState get() = _homeRecommendInternState.asStateFlow()

    private val _homeFilteringState =
        MutableStateFlow<UiState<HomeFilteringInfoModel>>(UiState.Loading)
    val homeFilteringState get() = _homeFilteringState.asStateFlow()

    private val _homeSortByState = MutableStateFlow(0)
    val homeSortByState get() = _homeSortByState.asStateFlow()

    private val _homeUserState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val homeUserState get() = _homeUserState.asStateFlow()

    private val _homeScrapViewState: MutableStateFlow<HomeScrapViewState> =
        MutableStateFlow(HomeScrapViewState())
    val homeScrapViewState: StateFlow<HomeScrapViewState> = _homeScrapViewState.asStateFlow()

    init {
        getProfile()
        getFilteringInfo()
        getHomeTodayInternList()
        getRecommendInternsData(
            sortBy = _homeSortByState.value,
            startYear = 2024,
            startMonth = 8,
        )
    }

    fun getRecommendInternsData(sortBy: Int, startYear: Int, startMonth: Int) {
        _homeRecommendInternState.value = UiState.Loading
        viewModelScope.launch {
            homeRepository.getRecommendIntern(SortBy.entries[sortBy].sortBy, startYear, startMonth)
                .onSuccess { internList ->
                    _homeRecommendInternState.value = UiState.Success(internList)
                }.onFailure { exception: Throwable ->
                    _homeRecommendInternState.value = UiState.Failure(exception.message ?: "")
                    _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
                }
        }
    }

    fun getHomeTodayInternList() {
        _homeTodayState.value = UiState.Loading
        viewModelScope.launch {
            homeRepository.getHomeTodayInternList().onSuccess { internList ->
                _homeTodayState.value = UiState.Success(internList)
            }.onFailure { exception: Throwable ->
                _homeTodayState.value = UiState.Failure(exception.message ?: "")
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun getFilteringInfo() {
        _homeFilteringState.value = UiState.Loading
        viewModelScope.launch {
            homeRepository.getFilteringInfo().onSuccess { filteringInfo ->
                _homeFilteringState.value = UiState.Success(filteringInfo)
            }.onFailure { exception: Throwable ->
                _homeFilteringState.value = UiState.Failure(exception.message ?: "")
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun putFilteringInfo(changeFilterRequest: ChangeFilteringRequestModel) {
        viewModelScope.launch {
            homeRepository.putFilteringInfo(
                changeFilterRequest
            ).onSuccess {
                _homeSideEffect.emit(HomeSideEffect.NavigateToHome)
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            myPageRepository.getProfile().onSuccess { response ->
                _homeUserState.value = UiState.Success(response.name)
            }.onFailure { exception: Throwable ->
                _homeUserState.value = UiState.Failure(exception.message ?: "")
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun postScrap(
        id: Long,
        color: Int,
    ) {
        viewModelScope.launch {
            scrapRepository.postScrap(
                ScrapRequestModel(id, color)
            ).onSuccess {
                updateScrapped(true)
            }.onFailure {
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun deleteScrap(
        scrapId: Long?,
        announcementId: Long,
    ) {
        viewModelScope.launch {
            scrapId?.let { ScrapRequestModel(it, null) }?.let { scrapRequestModel ->
                scrapRepository.deleteScrap(
                    scrapRequestModel
                ).onSuccess {
                    updateScrapped(false)
                }.onFailure {
                    _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
                }
            }
        }
    }

    fun updateSelectColor(newColor: Color) {
        _homeScrapViewState.update {
            it.copy(selectedColor = newColor)
        }
    }

    fun updateScrapDialogVisible(visible: Boolean) {
        _homeScrapViewState.update {
            it.copy(isScrapDialogVisible = visible)
        }
    }

    fun updateScrapped(scrapped: Boolean) {
        _homeScrapViewState.update {
            it.copy(isScrappedState = scrapped)
        }
    }

    fun updatePaletteOpen(open: Boolean) {
        _homeScrapViewState.update {
            it.copy(isPaletteOpen = open)
        }
    }

    fun updateColorChange(change: Boolean) {
        _homeScrapViewState.update {
            it.copy(isColorChange = change)
        }
    }
}