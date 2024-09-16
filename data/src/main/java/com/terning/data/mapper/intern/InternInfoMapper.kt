package com.terning.data.mapper.intern

import com.terning.data.dto.response.InternResponseDto
import com.terning.domain.entity.intern.InternInfo

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