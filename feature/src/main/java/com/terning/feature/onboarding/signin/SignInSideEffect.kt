package com.terning.feature.onboarding.signin

sealed class SignInSideEffect {
    data object NavigateToMain : SignInSideEffect()
    //    data class ShowToast(@StringRes val message: Int, val formatArgs: List<String?> = emptyList()) : SignInSideEffect()
    data class ShowToast(val message: String) : SignInSideEffect()
}