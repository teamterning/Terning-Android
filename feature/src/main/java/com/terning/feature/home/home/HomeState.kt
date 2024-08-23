package com.terning.feature.home.home

import com.terning.core.state.UiState
import com.terning.domain.entity.response.HomeFilteringInfoModel
import com.terning.domain.entity.response.HomeRecommendInternModel
import com.terning.domain.entity.response.HomeTodayInternModel
import com.terning.feature.home.home.model.SortBy

data class HomeState(
    val sortBy: SortBy = SortBy.EARLIEST,
    val homeUserNameState: UiState<String> = UiState.Loading,
    val homeFilteringInfoState: UiState<HomeFilteringInfoModel> = UiState.Loading,
    val homeTodayInternState: UiState<List<HomeTodayInternModel>> = UiState.Loading,
    val homeRecommendInternState: UiState<List<HomeRecommendInternModel>> = UiState.Loading,
)
