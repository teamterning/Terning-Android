package com.terning.feature.filtering.filteringone

sealed class FilteringOneSideEffect {
    data object NavigateUp : FilteringOneSideEffect()
    data class NavigateToFilteringTwo(val grade: String) : FilteringOneSideEffect()
}