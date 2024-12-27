package com.terning.data.home.mapper

import com.terning.data.home.dto.response.HomeFilteringInfoResponseDto
import com.terning.domain.home.entity.HomeFilteringInfo

fun HomeFilteringInfoResponseDto.toHomeFilteringInfo(): HomeFilteringInfo =
    HomeFilteringInfo(
        grade = this.grade,
        workingPeriod = this.workingPeriod,
        startYear = this.startYear,
        startMonth = this.startMonth,
        jobType = this.jobType,
    )