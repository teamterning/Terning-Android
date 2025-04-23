package com.terning.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.type.SortBy
import com.terning.domain.home.entity.ChangeFilteringRequestModel
import com.terning.domain.home.entity.HomeRecommendIntern
import com.terning.domain.home.entity.HomeRecommendedIntern
import com.terning.domain.home.repository.HomeRepository
import com.terning.domain.mypage.repository.MyPageRepository
import com.terning.domain.token.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.terning.core.designsystem.R as DesignSystemR

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val myPageRepository: MyPageRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val homeState get() = _homeState.asStateFlow()

    private val _homeSideEffect = MutableSharedFlow<HomeSideEffect>()
    val homeSideEffect get() = _homeSideEffect.asSharedFlow()

    private val scrapStateFlow: MutableStateFlow<Map<Long, Boolean>> =
        MutableStateFlow(emptyMap())

    private val _recommendInternFlow: MutableStateFlow<Flow<PagingData<HomeRecommendedIntern>>> =
        MutableStateFlow(flow { })

    @OptIn(ExperimentalCoroutinesApi::class)
    val recommendInternFlow: Flow<PagingData<HomeRecommendedIntern>> = combine(
        _recommendInternFlow.flatMapLatest {
            it.cachedIn(viewModelScope)
        }, scrapStateFlow
    ) { paging, scrapState ->
        paging.map { intern ->
            val isScrapped = scrapState[intern.internshipAnnouncementId] ?: intern.isScrapped
            intern.copy(
                isScrapped = isScrapped
            )
        }
    }

    fun getRecommendInternFlow() {
        refreshScrapStateFlow()
        refreshRecommendInternFlow()
    }

    private fun refreshScrapStateFlow() {
        scrapStateFlow.value = emptyMap()
    }

    private fun refreshRecommendInternFlow() {
        _recommendInternFlow.value = homeRepository.getRecommendIntern(
            sortBy = _homeState.value.sortBy.type
        ).cachedIn(viewModelScope)
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
                _homeSideEffect.emit(HomeSideEffect.ShowToast(DesignSystemR.string.server_failure))
            }
        }
    }

    fun getFilteringInfo() {
        viewModelScope.launch {
            homeRepository.getFilteringInfo().onSuccess { filteringInfo ->
                _homeState.value = _homeState.value.copy(
                    homeFilteringInfoState = UiState.Success(filteringInfo)
                )
            }.onFailure { exception: Throwable ->
                _homeState.value = _homeState.value.copy(
                    homeFilteringInfoState = UiState.Failure(exception.toString())
                )
                _homeSideEffect.emit(HomeSideEffect.ShowToast(DesignSystemR.string.server_failure))
            }
        }
    }

    fun putFilteringInfo(
        grade: String?,
        workingPeriod: String?,
        year: Int?,
        month: Int?,
        jobType: String
    ) {
        viewModelScope.launch {
            homeRepository.putFilteringInfo(
                ChangeFilteringRequestModel(
                    grade,
                    workingPeriod,
                    year,
                    month,
                    jobType
                )
            ).onSuccess {
                getFilteringInfo()
                refreshRecommendInternFlow()
            }
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
                _homeSideEffect.emit(HomeSideEffect.ShowToast(DesignSystemR.string.server_failure))
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

    fun updateSortBy(sortBy: Int) {
        _homeState.update {
            it.copy(
                sortBy = SortBy.entries[sortBy]
            )
        }
        refreshRecommendInternFlow()
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

    fun updateInternScrapState() {
        _homeState.value.homeInternModel?.run {
            val isScrapped = scrapStateFlow.value[this.internshipAnnouncementId] ?: this.isScrapped

            scrapStateFlow.update { currentMap ->
                currentMap + (this.internshipAnnouncementId to !isScrapped)
            }
        }
    }

    fun updateAlarmAvailability(availability: Boolean) {
        tokenRepository.setAlarmAvailable(availability)
    }

    fun completeNotificationCheck() {
        _homeState.update {
            it.copy(hasRequestedNotification = true)
        }
    }
}