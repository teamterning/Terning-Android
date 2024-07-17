package com.terning.feature.search.search

import InternshipAnnouncementModel
import com.terning.core.state.UiState

data class SearchState(
    var searchViewsList: UiState<List<InternshipAnnouncementModel>> = UiState.Loading,
    var searchScrapsList: UiState<List<InternshipAnnouncementModel>> = UiState.Loading,
)