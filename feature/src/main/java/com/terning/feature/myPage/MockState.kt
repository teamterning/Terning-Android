package com.terning.feature.myPage

import com.terning.core.state.UiState
import com.terning.domain.entity.response.MockResponseModel

data class MockState(
    var followers: UiState<List<MockResponseModel>> = UiState.Loading
)
