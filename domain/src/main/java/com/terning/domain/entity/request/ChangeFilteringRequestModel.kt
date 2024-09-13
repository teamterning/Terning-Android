package com.terning.domain.entity.request

data class ChangeFilteringRequestModel(
    val grade: String,
    val workingPeriod: String,
    val startYear: Int,
    val startMonth: Int,
)
