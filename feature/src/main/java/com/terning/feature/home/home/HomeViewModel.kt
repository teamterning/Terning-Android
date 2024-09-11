package com.terning.feature.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.extension.currentMonth
import com.terning.core.extension.currentYear
import com.terning.core.state.UiState
import com.terning.core.type.SortBy
import com.terning.domain.entity.home.HomeUpcomingIntern
import com.terning.domain.entity.request.ChangeFilteringRequestModel
import com.terning.domain.repository.HomeRepository
import com.terning.domain.repository.MyPageRepository
import com.terning.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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
) : ViewModel() {
    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val homeState get() = _homeState.asStateFlow()

    private val _homeSideEffect = MutableSharedFlow<HomeSideEffect>()
    val homeSideEffect get() = _homeSideEffect.asSharedFlow()

    init {
        getProfile()
        getFilteringInfo()
    }

    fun getRecommendInternsData(sortBy: Int, startYear: Int?, startMonth: Int?) {
        viewModelScope.launch {
            homeRepository.getRecommendIntern(
                sortBy = SortBy.entries[sortBy].type,
                startYear ?: Calendar.getInstance().currentYear,
                startMonth ?: Calendar.getInstance().currentMonth,
            ).onSuccess { internList ->
                _homeState.value = _homeState.value.copy(
                    homeRecommendInternState = UiState.Success(internList)
                )
            }.onFailure { exception: Throwable ->
                _homeState.value = _homeState.value.copy(
                    homeRecommendInternState = UiState.Failure(exception.toString())
                )
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun getHomeUpcomingInternList() {
        viewModelScope.launch {
            homeRepository.getHomeUpcomingInternList().onSuccess { internList ->
                _homeState.value = _homeState.value.copy(
                    homeUpcomingInternState = UiState.Success(internList)
                )
            }.onFailure { exception: Throwable ->
                _homeState.value = _homeState.value.copy(
                    homeUpcomingInternState = UiState.Failure(exception.toString())
                )
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun getFilteringInfo() {
        viewModelScope.launch {
            homeRepository.getFilteringInfo().onSuccess { filteringInfo ->
                _homeState.value = _homeState.value.copy(
                    homeFilteringInfoState = UiState.Success(filteringInfo)
                )
                if (filteringInfo.grade != null) {
                    getHomeUpcomingInternList()
                    getRecommendInternsData(
                        sortBy = _homeState.value.sortBy.ordinal,
                        startYear = filteringInfo.startYear,
                        startMonth = filteringInfo.startMonth,
                    )
                }
            }.onFailure { exception: Throwable ->
                _homeState.value = _homeState.value.copy(
                    homeFilteringInfoState = UiState.Failure(exception.toString())
                )
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun putFilteringInfo(grade: String, workingPeriod: String, year: Int, month: Int) {
        viewModelScope.launch {
            homeRepository.putFilteringInfo(
                ChangeFilteringRequestModel(grade, workingPeriod, year, month)
            )
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            myPageRepository.getProfile().onSuccess { response ->
                _homeState.value = _homeState.value.copy(
                    homeUserNameState = UiState.Success(response.name)
                )
            }.onFailure { exception: Throwable ->
                _homeState.value =
                    _homeState.value.copy(homeUserNameState = UiState.Failure(exception.toString()))
                _homeSideEffect.emit(HomeSideEffect.ShowToast(R.string.server_failure))
            }
        }
    }

    fun updateUpcomingDialogVisibility(visibility: Boolean) {
        _homeState.update {
            it.copy(homeUpcomingDialogVisibility = visibility)
        }
    }

    fun updateRecommendDialogVisibility(visibility: Boolean) {
        _homeState.update {
            it.copy(homeRecommendDialogVisibility = visibility)
        }
    }

    fun updateHomeInternModel(
        internshipAnnouncementId: Long,
        companyImage: String,
        title: String,
        dDay: String,
        deadline: String,
        workingPeriod: String,
        isScrapped: Boolean,
        color: String?,
        startYearMonth: String,
    ) {
        _homeState.update {
            it.copy(
                homeInternModel =
                HomeUpcomingIntern(
                    internshipAnnouncementId = internshipAnnouncementId,
                    companyImage = companyImage,
                    title = title,
                    dDay = dDay,
                    deadline = deadline,
                    workingPeriod = workingPeriod,
                    isScrapped = isScrapped,
                    color = color ?: "",
                    startYearMonth = startYearMonth,
                )
            )
        }
    }
}