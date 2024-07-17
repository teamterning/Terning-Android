package com.terning.feature.home.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.state.UiState
import com.terning.domain.entity.response.HomeRecommendInternModel
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.domain.repository.HomeRepository
import com.terning.feature.R
import com.terning.feature.home.home.model.InternFilterData
import com.terning.feature.home.home.model.UserNameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
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

    private val _homeSortByState = MutableStateFlow(0)
    val homeSortByState get() = _homeSortByState.asStateFlow()

    init {
        getHomeTodayInternList()
        getRecommendInternsData(
            sortBy = 0,
            startYear = 2024,
            startMonth = 8,
        )
    }

    private val _userName = mutableStateOf(
        UserNameState(
            userName = "남지우자랑스러운티엘이되",
            internFilter =
            InternFilterData(
                grade = 1,
                workingPeriod = 1,
                startYear = 2024,
                startMonth = 8,
            )
        )
    )
    val userName get() = _userName

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

    enum class SortBy(val sortBy: String) {
        EARLIEST("deadlineSoon"),
        SHORTEST("shortestDuration"),
        LONGEST("longestDuration"),
        SCRAP("mostScrapped"),
        VIEW_COUNT("mostViewed"),
    }
}