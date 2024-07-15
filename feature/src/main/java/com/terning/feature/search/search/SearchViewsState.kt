package com.terning.feature.search.search

import com.terning.core.state.UiState
import com.terning.domain.entity.response.SearchViewsResponseModel

data class SearchViewsState(
    var searchViewsList: UiState<List<SearchViewsResponseModel>> = UiState.Loading,
)