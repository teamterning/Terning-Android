package com.terning.data.home.mapper

import com.terning.domain.entity.home.HomeFilteringInfo

fun com.terning.data.home.dto.response.HomeFilteringInfoResponseDto.toHomeFilteringInfo(): HomeFilteringInfo =
    HomeFilteringInfo(
        grade = this.grade,
        workingPeriod = this.workingPeriod,
        startYear = this.startYear,
        startMonth = this.startMonth,
    )