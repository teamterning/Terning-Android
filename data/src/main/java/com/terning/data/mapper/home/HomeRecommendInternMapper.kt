package com.terning.data.mapper.home

import com.terning.data.dto.response.HomeRecommendInternResponseDto
import com.terning.domain.entity.home.HomeRecommendIntern

fun HomeRecommendInternResponseDto.toHomeRecommendIntern(): HomeRecommendIntern =
    HomeRecommendIntern(
        scrapId = this.scrapId,
        internshipAnnouncementId = this.internshipAnnouncementId,
        title = this.title,
        dDay = this.dDay,
        deadline = deadline,
        workingPeriod = this.workingPeriod,
        startYearMonth = this.startYearMonth,
        companyImage = this.companyImage,
        isScrapped = this.isScrapped,
    )
