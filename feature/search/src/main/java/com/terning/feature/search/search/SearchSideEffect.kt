package com.terning.feature.search.search

import androidx.annotation.StringRes

sealed class SearchSideEffect {
    data class ShowToast(@StringRes val message: Int) : SearchSideEffect()
}