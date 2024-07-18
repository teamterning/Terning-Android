package com.terning.feature.search.search.model

import com.terning.domain.entity.response.InternshipAnnouncementModel
import com.terning.core.state.UiState

data class SearchViewsListState(
    var searchViewsList: UiState<List<InternshipAnnouncementModel>> = UiState.Loading,
)