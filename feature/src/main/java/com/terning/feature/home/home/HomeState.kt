package com.terning.feature.home.home

import com.terning.core.state.UiState
import com.terning.core.type.SortBy
import com.terning.domain.entity.home.HomeFilteringInfo
import com.terning.domain.entity.home.HomeRecommendIntern
import com.terning.domain.entity.home.HomeUpcomingIntern

data class HomeState(
    val sortBy: SortBy = SortBy.EARLIEST,
    val sortingSheetVisibility: Boolean = false,
    val homeUserNameState: UiState<String> = UiState.Loading,
    val homeFilteringInfoState: UiState<HomeFilteringInfo> = UiState.Loading,
    val homeUpcomingInternState: UiState<HomeUpcomingIntern> = UiState.Empty,
    val homeRecommendInternState: UiState<HomeRecommendIntern> = UiState.Loading,
    val homeUpcomingDialogVisibility: Boolean = false,
    val homeRecommendDialogVisibility: Boolean = false,
    val homeInternModel: HomeRecommendIntern.HomeRecommendInternDetail? = null,
)
