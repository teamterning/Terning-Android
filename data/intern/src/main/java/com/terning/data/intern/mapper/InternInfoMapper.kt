package com.terning.data.intern.mapper

import com.terning.domain.entity.intern.InternInfo

fun com.terning.data.intern.dto.response.InternResponseDto.toInternInfo(): InternInfo =
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