package com.terning.data.filtering.mapper

import com.terning.data.filtering.dto.request.FilteringRequestDto
import com.terning.domain.entity.filtering.Filtering

fun Filtering.toFilteringRequestDto(): com.terning.data.filtering.dto.request.FilteringRequestDto =
    com.terning.data.filtering.dto.request.FilteringRequestDto(
        grade = grade,
        workingPeriod = workingPeriod,
        startYear = startYear,
        startMonth = startMonth
    )