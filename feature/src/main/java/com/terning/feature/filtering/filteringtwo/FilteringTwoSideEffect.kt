package com.terning.feature.filtering.filteringtwo

sealed class FilteringTwoSideEffect {
    data object NavigateUp : FilteringTwoSideEffect()
    data class NavigateToFilteringThree(val grade: String, val workingPeriod: String) :
        FilteringTwoSideEffect()
}