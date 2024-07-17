package com.terning.feature.onboarding.splash

import androidx.appcompat.app.AlertDialog

sealed class SplashState {
    data object AlertDialog : SplashState()
    data class GetHasAccessToken(val hasAccessToken: Boolean) : SplashState()
}
