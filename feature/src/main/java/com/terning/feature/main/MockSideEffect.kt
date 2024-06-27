package com.terning.feature.main

import androidx.annotation.StringRes

sealed class MockSideEffect {
    data class Toast(@StringRes val message: Int) : MockSideEffect()
}