package com.terning.domain.home.entity

data class ChangeFilteringRequestModel(
    val grade: String,
    val workingPeriod: String,
    val startYear: Int,
    val startMonth: Int,
)
