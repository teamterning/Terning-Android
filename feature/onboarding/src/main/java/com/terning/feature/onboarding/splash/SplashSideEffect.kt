package com.terning.feature.onboarding.splash

sealed class SplashSideEffect {
    data class HasAccessToken(
        val hasAccessToken: Boolean,
    ) : SplashSideEffect()
}
