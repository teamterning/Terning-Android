package com.terning.feature.mypage

import com.terning.core.state.UiState

data class MyPageState(
    val isLogoutAndQuitSuccess: UiState<Boolean> = UiState.Loading,
    val isGetSuccess: UiState<Boolean> = UiState.Loading,
    val name: String = "",
    val authType: String = "",
    val showNotice: Boolean = false,
    val showOpinion: Boolean = false,
    val showLogoutBottomSheet : Boolean = false,
    val showQuitBottomSheet : Boolean = false,
)