package com.terning.feature.onboarding.signin

import androidx.annotation.StringRes

sealed class SignInSideEffect {
    data object NavigateToHome : SignInSideEffect()
    data class NavigateSignUp(val authId: String) : SignInSideEffect()
    data class ShowToast(@StringRes val message: Int) : SignInSideEffect()
}