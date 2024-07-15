package com.terning.feature.search.searchprocess.models


data class SearchProcessState(
    val text: String = "",
    val query: String = "",
    val showSearchResults: Boolean = false,
    val existSearchResults: Boolean = false,
)