package com.terning.data.mapper.home

import com.terning.data.dto.response.HomeUpcomingInternResponseDto
import com.terning.domain.entity.home.HomeUpcomingIntern

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
