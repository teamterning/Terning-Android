package com.terning.feature.search.search.model

import InternshipAnnouncementModel
import com.terning.core.state.UiState

data class SearchScrapsListState(
    var searchScrapsList: UiState<List<InternshipAnnouncementModel>> = UiState.Loading,
)
