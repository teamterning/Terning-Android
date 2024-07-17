package com.terning.feature.search.search.model

import InternshipAnnouncementModel
import com.terning.core.state.UiState

data class SearchViewsListState(
    var searchViewsList: UiState<List<InternshipAnnouncementModel>> = UiState.Loading,
)