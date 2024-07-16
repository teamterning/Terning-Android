package com.terning.feature.search.search

import InternshipAnnouncement
import com.terning.core.state.UiState

data class SearchViewsState(
    var searchViewsList: UiState<List<InternshipAnnouncement>> = UiState.Loading,
)