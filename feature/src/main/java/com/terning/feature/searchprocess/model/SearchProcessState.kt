package com.terning.feature.searchprocess.model


data class SearchProcessState(
    val text: String = "",
    val query: String = "",
    val showSearchResults: Boolean = false,
)