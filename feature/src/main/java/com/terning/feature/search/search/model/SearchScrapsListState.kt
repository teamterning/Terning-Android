package com.terning.feature.search.search.model

import com.terning.domain.entity.response.SearchAnnouncement
import com.terning.core.state.UiState

data class SearchScrapsListState(
    var searchScrapsList: UiState<List<SearchAnnouncement>> = UiState.Loading,
)
