package com.terning.feature.search.searchprocess.models

import com.terning.core.state.UiState
import com.terning.domain.entity.response.SearchResultModel
import com.terning.feature.home.home.model.InternData


data class SearchProcessState(
    val text: String = "",
    val query: String = "",
    val showSearchResults: Boolean = false,
    val existSearchResults: Boolean = false,
    val searchResults: List<InternData> = emptyList(),
)