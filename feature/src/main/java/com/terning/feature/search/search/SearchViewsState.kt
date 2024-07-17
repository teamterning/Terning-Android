package com.terning.feature.search.search

import InternshipAnnouncementModel
import com.terning.core.state.UiState

data class SearchViewsState(
    var searchViewsList: UiState<List<InternshipAnnouncementModel>> = UiState.Loading,
)