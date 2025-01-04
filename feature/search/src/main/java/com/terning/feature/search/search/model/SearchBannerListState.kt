package com.terning.feature.search.search.model

import com.terning.core.designsystem.state.UiState
import com.terning.domain.search.entity.SearchBanner

data class SearchBannerListState(
    var searchBannersList: UiState<List<SearchBanner>> = UiState.Loading,
)