package com.terning.feature.intern

import androidx.annotation.StringRes

sealed class InternViewSideEffect {
    data class ShowToast(@StringRes val message: Int) :
        InternViewSideEffect()
}