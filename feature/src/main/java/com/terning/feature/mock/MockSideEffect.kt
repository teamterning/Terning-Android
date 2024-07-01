package com.terning.feature.mock

import androidx.annotation.StringRes

sealed class MockSideEffect {
    data class Toast(@StringRes val message: Int) : MockSideEffect()
}