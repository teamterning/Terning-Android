package com.terning.feature.search.search

import androidx.annotation.StringRes

sealed class SearchViewsSideEffect {
    data class Toast(@StringRes val message: Int) : SearchViewsSideEffect()
}