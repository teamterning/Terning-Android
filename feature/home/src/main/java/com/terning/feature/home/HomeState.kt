package com.terning.feature.home

import com.terning.core.designsystem.state.UiState
import com.terning.core.designsystem.type.SortBy
import com.terning.domain.home.entity.HomeFilteringInfo
import com.terning.domain.home.entity.HomeRecommendIntern
import com.terning.domain.home.entity.HomeUpcomingIntern

data class HomeState(
    val totalCount: Int = 0,
    val sortBy: SortBy = SortBy.EARLIEST,
    val sortingSheetVisibility: Boolean = false,
    val homeUserNameState: UiState<String> = UiState.Loading,
    val homeFilteringInfoState: UiState<HomeFilteringInfo> = UiState.Loading,
    val homeUpcomingInternState: UiState<HomeUpcomingIntern> = UiState.Empty,
    val homeRecommendInternState: UiState<HomeRecommendIntern> = UiState.Loading,
    val homeRecommendDialogVisibility: Boolean = false,
    val homeInternModel: HomeRecommendIntern.HomeRecommendInternDetail? = null,
    val hasRequestedNotification: Boolean = false
)
