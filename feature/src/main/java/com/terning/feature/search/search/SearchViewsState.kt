package com.terning.feature.search.search

import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternshipAnnouncement

data class SearchViewsState(
    var searchViewsList: UiState<List<InternshipAnnouncement>> = UiState.Loading,
)