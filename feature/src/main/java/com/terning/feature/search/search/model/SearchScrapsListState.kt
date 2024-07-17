package com.terning.feature.search.search.model

import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternshipAnnouncementModel

data class SearchScrapsListState(
    var searchScrapsList: UiState<List<InternshipAnnouncementModel>> = UiState.Loading,
)