package com.terning.feature.search.search

import com.terning.core.state.UiState
import com.terning.domain.entity.response.InternAnnouncementResponseModel

data class SearchViewsState(
    var searchViewsList: UiState<List<InternAnnouncementResponseModel>> = UiState.Loading,
)