package com.terning.feature.search.search

import InternshipAnnouncement
import com.terning.core.state.UiState

data class SearchState(
    var searchViewsList: UiState<List<InternshipAnnouncement>> = UiState.Loading,
    var searchScrapsList: UiState<List<InternshipAnnouncement>> = UiState.Loading,
)