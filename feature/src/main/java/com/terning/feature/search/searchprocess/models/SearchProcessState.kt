package com.terning.feature.search.searchprocess.models

import com.terning.core.state.UiState
import com.terning.domain.entity.search.SearchResult

data class SearchProcessState(
    val loadState: UiState<SearchResult> = UiState.Loading,
    val text: String = "",
    val keyword: String = "",
    val showSearchResults: Boolean = false,
    val existSearchResults: Boolean = false,
    val isScrapDialogVisible: Boolean = false,
    val scrapped: Boolean = false,
    val selectedInternIndex: Int = 0,
    val sheetState: Boolean = false,
)
