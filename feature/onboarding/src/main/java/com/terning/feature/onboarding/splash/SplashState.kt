package com.terning.feature.onboarding.splash

sealed class SplashState {
    data class HasAccessToken(val hasAccessToken: Boolean) : SplashState()
}
