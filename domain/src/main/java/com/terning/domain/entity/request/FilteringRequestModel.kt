package com.terning.domain.entity.request

data class FilteringRequestModel(
    val grade: Int,
    val workingPeriod: Int,
    val startYear: Int,
    val startMonth: Int
)