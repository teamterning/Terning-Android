package com.terning.feature.filtering.filteringtwo

sealed class FilteringTwoSideEffect {
    data object NavigateUp : FilteringTwoSideEffect()
}