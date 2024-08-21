package com.terning.data.mapper.search

import com.terning.data.dto.response.SearchAnnouncementResponseDto
import com.terning.domain.entity.search.SearchPopularAnnouncement

fun SearchAnnouncementResponseDto.toSearchPopularAnnouncementList(): List<SearchPopularAnnouncement> {
    return announcements.map {
        SearchPopularAnnouncement(
            announcementId = it.internshipAnnouncementId,
            companyImage = it.companyImage,
            title = it.title,
        )
    }
}