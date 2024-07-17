package com.terning.feature.home.home

import androidx.annotation.StringRes

sealed class HomeSideEffect {
    data object NavigateToChangeFilter : HomeSideEffect()
    data class ShowToast(@StringRes val message: Int) : HomeSideEffect()
}