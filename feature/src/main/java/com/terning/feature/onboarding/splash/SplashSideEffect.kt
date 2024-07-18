package com.terning.feature.onboarding.splash

import androidx.annotation.StringRes

sealed class SplashSideEffect {
    data class GetHasRefreshToken(val refreshToken: Boolean) : SplashSideEffect()
    data object NavigateToHome : SplashSideEffect()
    data class ShowToast(@StringRes val message: Int) : SplashSideEffect()
}
