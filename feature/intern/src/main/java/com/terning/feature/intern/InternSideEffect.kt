package com.terning.feature.intern

import androidx.annotation.StringRes

sealed class InternSideEffect {
    data class Toast(@StringRes val message: Int) :
        InternSideEffect()
    
}