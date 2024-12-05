package com.terning.feature.onboarding.signup

import androidx.annotation.StringRes

sealed class SignUpSideEffect {
    data object NavigateToStartFiltering : SignUpSideEffect()
    data class ShowToast(@StringRes val message: Int) : SignUpSideEffect()
}