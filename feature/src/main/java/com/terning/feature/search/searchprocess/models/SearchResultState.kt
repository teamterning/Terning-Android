package com.terning.feature.search.searchprocess.models

import com.terning.core.state.UiState
import com.terning.domain.entity.response.SearchResultModel

data class SearchResultState(
    var searchListState: UiState<List<SearchResultModel>> = UiState.Loading,
)