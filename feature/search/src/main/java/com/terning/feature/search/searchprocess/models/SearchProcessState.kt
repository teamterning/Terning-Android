package com.terning.feature.search.searchprocess.models

import com.terning.core.state.UiState
import com.terning.core.type.SortBy
import com.terning.domain.search.entity.SearchResult

data class SearchProcessState(
    val loadState: UiState<com.terning.domain.search.entity.SearchResult> = UiState.Loading,
    val currentSortBy: Int = 0,
    val text: String = "",
    val keyword: String = "",
    val sortBy: SortBy = SortBy.EARLIEST,
    val page: Int = 0,
    val size: Int = 100,
    val showSearchResults: Boolean = false,
    val existSearchResults: Boolean = false,
    val isScrapDialogVisible: Boolean = false,
    val scrapped: Boolean = false,
    val selectedInternIndex: Int = 0,
    val sheetState: Boolean = false,
    val changeFilterState: Boolean = false,
    val searchResult: com.terning.domain.search.entity.SearchResult = com.terning.domain.search.entity.SearchResult(
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