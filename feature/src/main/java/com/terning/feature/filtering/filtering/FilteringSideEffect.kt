package com.terning.feature.filtering.filtering

import androidx.annotation.StringRes

sealed class FilteringSideEffect {
    data object NavigateToStartHome : FilteringSideEffect()
    data class ShowToast(@StringRes val message: Int) : FilteringSideEffect()
}