package com.terning.feature.onboarding.filtering

data class FilteringState(
    val grade: Int = -1,
    val workingPeriod: Int = -1,
    val startYear: Int = -1,
    val startMonth: Int = -1
)