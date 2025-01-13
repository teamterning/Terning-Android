package com.terning.data.search.mapper

import com.terning.data.search.dto.response.SearchResultResponseDto
import com.terning.domain.search.entity.SearchResult

fun SearchResultResponseDto.SearchAnnouncementDto.toSearchResultList(totalCount: Int): SearchResult =
    SearchResult(
        totalCount = totalCount,
        internshipAnnouncementId = this.internshipAnnouncementId,
        title = this.title,
        dDay = this.dDay,
        workingPeriod = this.workingPeriod,
        companyImage = this.companyImage,
        isScrapped = this.isScrapped,
        deadline = this.deadline,
        startYearMonth = this.startYearMonth,
        color = this.color,
    )