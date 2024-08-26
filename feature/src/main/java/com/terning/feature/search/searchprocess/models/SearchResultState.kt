package com.terning.feature.search.searchprocess.models

import com.terning.core.state.UiState
import com.terning.domain.entity.search.SearchResult

data class SearchResultState(
    var searchListState: UiState<List<SearchResult>> = UiState.Loading,
)