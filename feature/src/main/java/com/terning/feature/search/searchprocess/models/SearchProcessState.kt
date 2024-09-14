package com.terning.feature.search.searchprocess.models

import com.terning.core.state.UiState
import com.terning.core.type.SortBy
import com.terning.domain.entity.search.SearchResult

data class SearchProcessState(
    val loadState: UiState<SearchResult> = UiState.Loading,
    val currentSortBy: Int = 0,
    val text: String = "",
    val keyword: String = "",
    val sortBy: SortBy = SortBy.EARLIEST,
    val page: Int = 0,
    val size: Int = 10,
    val showSearchResults: Boolean = false,
    val existSearchResults: Boolean = false,
    val isScrapDialogVisible: Boolean = false,
    val scrapped: Boolean = false,
    val selectedInternIndex: Int = 0,
    val sheetState: Boolean = false,
    val changeFilterState: Boolean = false,
    val searchResult: SearchResult = SearchResult(
        internshipAnnouncementId = 0,
        title = "",
        companyImage = "",
        dDay = "",
        workingPeriod = "",
        isScrapped = false,
        deadline = "",
        startYearMonth = "",
        color = "",
    ),
)
