package com.terning.data.filtering.mapper

import com.terning.data.filtering.dto.request.FilteringRequestDto
import com.terning.domain.filtering.entity.Filtering

fun Filtering.toFilteringRequestDto(): FilteringRequestDto =
   FilteringRequestDto(
        grade = grade,
        workingPeriod = workingPeriod,
        startYear = startYear,
        startMonth = startMonth
    )