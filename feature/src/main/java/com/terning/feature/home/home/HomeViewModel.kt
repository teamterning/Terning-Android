package com.terning.feature.home.home

import androidx.compose.runtime.getValue
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

    init {
        getHomeTodayInternList()

        with(_userName.internFilter) {
            getRecommendInternsData(
                sortBy = 0,
                this?.startYear ?: currentYear,
                this?.startMonth ?: currentMonth
            )
        }
    }

    private val _userName by mutableStateOf(
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

    private val _scrapState = MutableStateFlow(
        UserScrapState(
            isScrapExist = true,
            scrapData = getScrapData()
        )
    )
    val scrapData get() = _scrapState.asStateFlow()

    private val _recommendInternState =
        MutableStateFlow<UiState<List<HomeRecommendInternModel>>>(UiState.Loading)
    val recommendInternState get() = _recommendInternState.asStateFlow()

    private val _homeSideEffect = MutableSharedFlow<HomeSideEffect>()
    val homeSideEffect get() = _homeSideEffect.asSharedFlow()

    private val _homeSortByState = MutableStateFlow(0)
    val homeSortByState get() = _homeSortByState.asStateFlow()

    fun setGrade(grade: Int) {
        userName.internFilter?.grade = grade
    }

    fun setWorkingPeriod(workingPeriod: Int) {
        userName.internFilter?.workingPeriod = workingPeriod
    }

    fun getRecommendInternsData(sortBy: Int, year: Int, month: Int) {
        _recommendInternState.value = UiState.Loading
        viewModelScope.launch {
            homeRepository.getRecommendIntern(SortBy.entries[sortBy].sortBy, year, month)
                .onSuccess { internList ->
                    _recommendInternState.value = UiState.Success(internList)
                }.onFailure { exception: Throwable ->
                    _recommendInternState.value = UiState.Failure(exception.message ?: "")
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