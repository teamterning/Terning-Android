package com.terning.data.mapper.home

import com.terning.data.dto.response.HomeUpcomingInternResponseDto
import com.terning.domain.entity.home.HomeUpcomingIntern

fun HomeUpcomingInternResponseDto.toHomeUpcomingInternList(): HomeUpcomingIntern =
    HomeUpcomingIntern(
        internshipAnnouncementId = this.internshipAnnouncementId,
        companyImage = this.companyImage,
        title = this.title,
        dDay = this.dDay,
        deadline = this.deadline,
        workingPeriod = this.workingPeriod,
        isScrapped = this.isScrapped,
        startYearMonth = this.startYearMonth,
        color = this.color,
        companyInfo = this.companyInfo,
    )
