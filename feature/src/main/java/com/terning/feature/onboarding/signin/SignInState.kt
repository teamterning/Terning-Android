package com.terning.feature.onboarding.signin

import com.terning.core.state.UiState

data class SignInState (
    val accessToken: UiState<String>? = UiState.Loading
)