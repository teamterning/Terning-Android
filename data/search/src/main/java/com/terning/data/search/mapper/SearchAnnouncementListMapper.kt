package com.terning.data.search.mapper

import com.terning.data.search.dto.response.SearchAnnouncementResponseDto
import com.terning.domain.search.entity.SearchPopularAnnouncement

fun SearchAnnouncementResponseDto.toSearchPopularAnnouncementList(): List<SearchPopularAnnouncement> {
    return announcements.map {
        SearchPopularAnnouncement(
            announcementId = it.internshipAnnouncementId,
            companyImage = it.companyImage,
            title = it.title,
        )
    }
}