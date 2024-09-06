package com.terning.feature.home.home

import com.terning.core.state.UiState
import com.terning.domain.entity.home.HomeFilteringInfo
import com.terning.domain.entity.home.HomeRecommendIntern
import com.terning.domain.entity.home.HomeUpcomingIntern
import com.terning.feature.home.home.model.SortBy

data class HomeState(
    val sortBy: SortBy = SortBy.EARLIEST,
    val homeUserNameState: UiState<String> = UiState.Loading,
    val homeFilteringInfoState: UiState<HomeFilteringInfo> = UiState.Loading,
    val homeUpcomingInternState: UiState<List<HomeUpcomingIntern>> = UiState.Loading,
    val homeRecommendInternState: UiState<List<HomeRecommendIntern>> = UiState.Loading,
)
