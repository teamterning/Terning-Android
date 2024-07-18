package com.terning.feature.onboarding.splash

sealed class SplashState {
    data class GetHasAccessToken(val hasAccessToken: Boolean) : SplashState()
}
