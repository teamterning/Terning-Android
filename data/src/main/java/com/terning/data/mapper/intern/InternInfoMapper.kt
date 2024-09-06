package com.terning.data.mapper.intern

import com.terning.domain.entity.intern.InternInfo

fun InternInfo.toInternInfo(): InternInfo =
    InternInfo(
        dDay = dDay,
        title = title,
        deadline = deadline,
        workingPeriod = workingPeriod,
        startDate = startDate,
        scrapCount = scrapCount,
        viewCount = viewCount,
        company = company,
        companyCategory = companyCategory,
        companyImage = companyImage,
        qualification = qualification,
        jobType = jobType,
        detail = detail,
        url = url,
        scrapId = scrapId
    )