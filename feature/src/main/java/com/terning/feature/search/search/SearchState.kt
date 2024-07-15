package com.terning.feature.search.search

import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternScrapsResponseModel
import com.terning.domain.entity.response.InternViewsResponseModel

data class SearchState(
    var searchViewsList: UiState<List<InternViewsResponseModel>> = UiState.Loading,
    var searchScrapsList: UiState<List<InternScrapsResponseModel>> = UiState.Loading,
)