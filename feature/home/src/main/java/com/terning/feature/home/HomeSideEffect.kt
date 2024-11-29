package com.terning.feature.home

import androidx.annotation.StringRes

sealed class HomeSideEffect {
    data class ShowToast(@StringRes val message: Int) : HomeSideEffect()
    data object NavigateToHome : HomeSideEffect()
}