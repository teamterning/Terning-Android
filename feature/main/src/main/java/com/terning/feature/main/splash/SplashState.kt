package com.terning.feature.main.splash

sealed class SplashState {
    data class HasAccessToken(val hasAccessToken: Boolean) : SplashState()
}
