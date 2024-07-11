package com.terning.feature.search.searchprocess.model


data class SearchProcessState(
    val text: String = "",
    val query: String = "",
    val showSearchResults: Boolean = false,
)