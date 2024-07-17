package com.terning.feature.search.search

import com.terning.domain.entity.response.InternshipAnnouncementModel
import com.terning.core.state.UiState

data class SearchViewsState(
    var searchViewsList: UiState<List<InternshipAnnouncementModel>> = UiState.Loading,
)