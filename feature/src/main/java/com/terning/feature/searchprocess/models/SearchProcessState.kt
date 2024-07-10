package com.terning.feature.searchprocess.models


data class SearchProcessState(
    val text: String = "",
    val query: String = "",
    val showSearchResults: Boolean = false,
)