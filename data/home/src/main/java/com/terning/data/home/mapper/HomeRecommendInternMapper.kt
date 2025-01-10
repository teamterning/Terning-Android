package com.terning.data.home.mapper

import com.terning.data.home.dto.response.HomeRecommendInternResponseDto
import com.terning.domain.home.entity.HomeRecommendedIntern


fun HomeRecommendInternResponseDto.Result.toHomeRecommendedIntern(totalCount: Int): HomeRecommendedIntern =
    HomeRecommendedIntern(
        totalCount = totalCount,
        internshipAnnouncementId = this.internshipAnnouncementId,
        companyImage = this.companyImage,
        dDay = this.dDay,
        title = this.title,
        workingPeriod = this.workingPeriod,
        isScrapped = this.isScrapped,
        color = this.color,
        deadline = this.deadline,
        startYearMonth = this.startYearMonth,
    )