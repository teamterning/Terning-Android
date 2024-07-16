package com.terning.feature.onboarding.signin

import androidx.annotation.StringRes

sealed class SignInSideEffect {
    data object NavigateToHome : SignInSideEffect()
    data object NavigateSignUp : SignInSideEffect()
    data class ShowToast(@StringRes val message: Int) : SignInSideEffect()
}