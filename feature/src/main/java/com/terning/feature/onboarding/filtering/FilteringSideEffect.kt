package com.terning.feature.onboarding.filtering

import androidx.annotation.StringRes

sealed class FilteringSideEffect {
    data object NavigateToHome : FilteringSideEffect()
    data class ShowToast(@StringRes val message: Int) : FilteringSideEffect()
}