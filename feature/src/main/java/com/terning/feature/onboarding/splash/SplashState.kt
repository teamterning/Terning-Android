package com.terning.feature.onboarding.splash

sealed class SplashState {
    data object AlertDialog : SplashState()
    data class GetHasAccessToken(val hasAccessToken: Boolean) : SplashState()
}
