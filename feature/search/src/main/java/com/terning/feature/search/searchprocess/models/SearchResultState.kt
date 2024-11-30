package com.terning.feature.search.searchprocess.models

import com.terning.core.designsystem.state.UiState
import com.terning.domain.search.entity.SearchResult

data class SearchResultState(
    var searchListState: UiState<List<SearchResult>> = UiState.Loading,
)