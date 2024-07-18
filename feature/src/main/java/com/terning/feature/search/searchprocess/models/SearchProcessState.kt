package com.terning.feature.search.searchprocess.models

import com.terning.feature.home.home.model.InternData


data class SearchProcessState(
    val text: String = "",
    val keyword: String = "",
    val showSearchResults: Boolean = false,
    val existSearchResults: Boolean = false,
    val searchResults: List<InternData> = emptyList(),
)