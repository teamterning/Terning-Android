package com.terning.feature.search.searchprocess.models


data class SearchProcessState(
    val text: String = "",
    val keyword: String = "",
    val showSearchResults: Boolean = false,
    val existSearchResults: Boolean = false,
//    val searchResults: List<InternData> = emptyList(),
)