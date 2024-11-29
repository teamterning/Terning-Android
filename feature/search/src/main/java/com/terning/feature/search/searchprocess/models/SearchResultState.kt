package com.terning.feature.search.searchprocess.models

import com.terning.core.state.UiState
import com.terning.domain.search.entity.SearchResult

data class SearchResultState(
    var searchListState: UiState<List<com.terning.domain.search.entity.SearchResult>> = UiState.Loading,
)