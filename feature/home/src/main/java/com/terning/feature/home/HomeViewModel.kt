package com.terning.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.type.SortBy
import com.terning.domain.home.entity.ChangeFilteringRequestModel
import com.terning.domain.home.entity.HomeRecommendIntern
import com.terning.domain.home.repository.HomeRepository
import com.terning.domain.mypage.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    fun getRecommendInternsData(sortBy: Int) {
        viewModelScope.launch {
            homeRepository.getRecommendIntern(
                sortBy = SortBy.entries[sortBy].type,
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
            homeRepository.getHomeUpcomingInternList().onSuccess { upcomingIntern ->
                _homeState.value = _homeState.value.copy(
                    homeUpcomingInternState = UiState.Success(upcomingIntern)
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
                    getRecommendInternsData(
                        sortBy = _homeState.value.sortBy.ordinal,
                    )
                    getHomeUpcomingInternList()
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
                ChangeFilteringRequestModel(
                    grade,
                    workingPeriod,
                    year,
                    month
                )
            )
        }
    }

    fun getProfile() {
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
                HomeRecommendIntern.HomeRecommendInternDetail(
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

    fun updateSortBy(sortBy: Int, startYear: Int?, startMonth: Int?) {
        _homeState.update {
            it.copy(
                sortBy = SortBy.entries[sortBy]
            )
        }
        getRecommendInternsData(
            _homeState.value.sortBy.ordinal,
        )
    }

    fun updateSortingSheetVisibility(visibility: Boolean) {
        _homeState.update {
            it.copy(
                sortingSheetVisibility = visibility
            )
        }
    }

    fun navigateCalendar() {
        viewModelScope.launch {
            _homeSideEffect.emit(HomeSideEffect.NavigateToCalendar)
        }
    }

    fun navigateIntern(announcementId: Long) {
        viewModelScope.launch {
            _homeSideEffect.emit(HomeSideEffect.NavigateToIntern(announcementId))
        }
    }
}