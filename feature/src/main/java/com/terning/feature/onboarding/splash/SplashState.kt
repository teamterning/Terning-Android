package com.terning.feature.onboarding.splash

sealed class SplashState {
    data class GetAccessToken(val hasAccessToken: Boolean) : SplashState()
}
