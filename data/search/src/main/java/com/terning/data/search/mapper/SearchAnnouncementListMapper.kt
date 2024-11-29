package com.terning.data.search.mapper

import com.terning.domain.entity.search.SearchPopularAnnouncement

fun com.terning.data.search.dto.response.SearchAnnouncementResponseDto.toSearchPopularAnnouncementList(): List<SearchPopularAnnouncement> {
    return announcements.map {
        SearchPopularAnnouncement(
            announcementId = it.internshipAnnouncementId,
            companyImage = it.companyImage,
            title = it.title,
        )
    }
}