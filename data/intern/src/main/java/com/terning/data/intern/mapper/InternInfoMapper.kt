package com.terning.data.intern.mapper

import com.terning.data.intern.dto.response.InternResponseDto
import com.terning.domain.intern.entity.InternInfo

fun InternResponseDto.toInternInfo(): InternInfo =
    InternInfo(
        dDay = dDay,
        title = title,
        deadline = deadline,
        workingPeriod = workingPeriod,
        startYearMonth = startYearMonth,
        scrapCount = scrapCount,
        viewCount = viewCount,
        company = company,
        companyCategory = companyCategory,
        companyImage = companyImage,
        qualification = qualification,
        jobType = jobType,
        detail = detail,
        url = url,
        isScrapped = isScrapped,
        color = color,
    )