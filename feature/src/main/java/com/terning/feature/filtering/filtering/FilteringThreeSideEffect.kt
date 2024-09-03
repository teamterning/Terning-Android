package com.terning.feature.filtering.filtering

import androidx.annotation.StringRes

sealed class FilteringThreeSideEffect {
    data object NavigateToStartHome : FilteringThreeSideEffect()
    data class ShowToast(@StringRes val message: Int) : FilteringThreeSideEffect()
}