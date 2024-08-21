package com.terning.data.mapper.home

import com.terning.data.dto.response.HomeTodayInternResponseDto
import com.terning.domain.entity.home.HomeTodayIntern

fun HomeTodayInternResponseDto.toHomeTodayInternList(): HomeTodayIntern =
    HomeTodayIntern(
        scrapId = this.scrapId,
        internshipAnnouncementId = this.internshipAnnouncementId,
        companyImage = this.companyImage,
        title = this.title,
        dDay = this.dDay,
        deadline = this.deadline,
        workingPeriod = this.workingPeriod,
        startYearMonth = this.startYearMonth,
        color = this.color,
    )