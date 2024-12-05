package com.terning.data.home.mapper

import com.terning.data.home.dto.response.HomeUpcomingInternResponseDto
import com.terning.domain.home.entity.HomeUpcomingIntern

fun HomeUpcomingInternResponseDto.toHomeUpcomingInternList(): HomeUpcomingIntern =
    HomeUpcomingIntern(
        hasScrapped = this.hasScrapped,
        homeUpcomingInternDetail = this.scraps.map {
            it.toHomeUpcomingInternDetail()
        },
    )

fun HomeUpcomingInternResponseDto.HomeUpcomingInternList.toHomeUpcomingInternDetail(): HomeUpcomingIntern.HomeUpcomingInternDetail =
    HomeUpcomingIntern.HomeUpcomingInternDetail(
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
