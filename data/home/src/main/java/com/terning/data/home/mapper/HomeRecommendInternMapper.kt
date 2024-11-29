package com.terning.data.home.mapper

import com.terning.domain.entity.home.HomeRecommendIntern

fun com.terning.data.home.dto.response.HomeRecommendInternResponseDto.toHomeRecommendInternList(): HomeRecommendIntern =
    HomeRecommendIntern(
        totalCount = this.totalCount,
        homeRecommendInternDetail = this.result.map {
            it.toHomeRecommendInternDetail()
        }
    )

fun com.terning.data.home.dto.response.HomeRecommendInternResponseDto.Result.toHomeRecommendInternDetail(): HomeRecommendIntern.HomeRecommendInternDetail =
    HomeRecommendIntern.HomeRecommendInternDetail(
        internshipAnnouncementId = this.internshipAnnouncementId,
        title = this.title,
        dDay = this.dDay,
        deadline = deadline,
        workingPeriod = this.workingPeriod,
        startYearMonth = this.startYearMonth,
        companyImage = this.companyImage,
        isScrapped = this.isScrapped,
        color = this.color,
    )