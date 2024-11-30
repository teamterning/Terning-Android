package com.terning.feature.search.search.model

import com.terning.core.designsystem.state.UiState
import com.terning.domain.search.entity.SearchPopularAnnouncement

data class SearchViewsListState(
    var searchViewsList: UiState<List<SearchPopularAnnouncement>> = UiState.Loading,
)