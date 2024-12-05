package com.terning.data.home.mapper

import com.terning.data.home.dto.response.HomeRecommendInternResponseDto
import com.terning.domain.home.entity.HomeRecommendIntern

fun HomeRecommendInternResponseDto.toHomeRecommendInternList(): HomeRecommendIntern =
    HomeRecommendIntern(
        totalCount = this.totalCount,
        homeRecommendInternDetail = this.result.map {
            it.toHomeRecommendInternDetail()
        }
    )

fun HomeRecommendInternResponseDto.Result.toHomeRecommendInternDetail(): HomeRecommendIntern.HomeRecommendInternDetail =
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