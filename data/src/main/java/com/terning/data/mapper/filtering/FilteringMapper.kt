package com.terning.data.mapper.filtering

import com.terning.data.dto.request.FilteringRequestDto
import com.terning.domain.entity.filtering.Filtering

fun Filtering.toFilteringRequestDto(): FilteringRequestDto =
    FilteringRequestDto(
        grade = grade,
        workingPeriod = workingPeriod,
        startYear = startYear,
        startMonth = startMonth
    )