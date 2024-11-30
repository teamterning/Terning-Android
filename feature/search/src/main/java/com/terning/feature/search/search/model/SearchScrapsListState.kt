package com.terning.feature.search.search.model

import com.terning.core.designsystem.state.UiState
import com.terning.domain.search.entity.SearchPopularAnnouncement

data class SearchScrapsListState(
    var searchScrapsList: UiState<List<SearchPopularAnnouncement>> = UiState.Loading,
)
