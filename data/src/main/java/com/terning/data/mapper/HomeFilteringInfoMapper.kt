package com.terning.data.mapper

import com.terning.data.dto.response.HomeFilteringInfoResponseDto
import com.terning.domain.entity.HomeFilteringInfo

fun HomeFilteringInfoResponseDto.toHomeFilteringInfo(): HomeFilteringInfo =
    HomeFilteringInfo(
        grade = this.grade,
        workingPeriod = this.workingPeriod,
        startYear = this.startYear,
        startMonth = this.startMonth,
    )