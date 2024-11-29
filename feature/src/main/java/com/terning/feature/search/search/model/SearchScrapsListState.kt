package com.terning.feature.search.search.model

import com.terning.domain.search.entity.SearchPopularAnnouncement
import com.terning.core.state.UiState

data class SearchScrapsListState(
    var searchScrapsList: UiState<List<com.terning.domain.search.entity.SearchPopularAnnouncement>> = UiState.Loading,
)
