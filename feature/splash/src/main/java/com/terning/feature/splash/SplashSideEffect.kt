package com.terning.feature.splash

sealed class SplashSideEffect {
    data class HasAccessToken(val hasAccessToken: Boolean) : SplashSideEffect()
}
