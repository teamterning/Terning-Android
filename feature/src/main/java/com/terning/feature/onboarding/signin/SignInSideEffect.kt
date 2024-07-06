package com.terning.feature.onboarding.signin

import androidx.annotation.StringRes

sealed class SignInSideEffect {
    data object NavigateToMain : SignInSideEffect()
    data class ShowToast(@StringRes val message: Int) : SignInSideEffect()
}