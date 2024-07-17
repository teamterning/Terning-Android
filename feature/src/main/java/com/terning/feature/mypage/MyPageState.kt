package com.terning.feature.mypage

import com.terning.core.state.UiState

data class MyPageState(
    val isSuccess: UiState<Boolean> = UiState.Loading
)