package com.terning.domain.entity.request

data class ChangeFilteringRequestModel(
    val grade: Int,
    val workingPeriod: Int,
    val startYear: Int,
    val startMonth: Int,
)
