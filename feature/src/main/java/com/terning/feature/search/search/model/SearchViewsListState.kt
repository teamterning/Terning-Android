package com.terning.feature.search.search.model

import com.terning.domain.entity.search.SearchAnnouncement
import com.terning.core.state.UiState

data class SearchViewsListState(
    var searchViewsList: UiState<List<SearchAnnouncement>> = UiState.Loading,
)