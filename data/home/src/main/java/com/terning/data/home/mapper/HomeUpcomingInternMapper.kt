package com.terning.data.home.mapper

import com.terning.domain.entity.home.HomeUpcomingIntern

fun com.terning.data.home.dto.response.HomeUpcomingInternResponseDto.toHomeUpcomingInternList(): HomeUpcomingIntern =
    HomeUpcomingIntern(
        hasScrapped = this.hasScrapped,
        homeUpcomingInternDetail = this.scraps.map {
            it.toHomeUpcomingInternDetail()
        },
    )

fun com.terning.data.home.dto.response.HomeUpcomingInternResponseDto.HomeUpcomingInternList.toHomeUpcomingInternDetail(): HomeUpcomingIntern.HomeUpcomingInternDetail =
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
