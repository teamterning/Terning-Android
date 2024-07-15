package com.terning.domain.repository

import com.terning.domain.entity.response.InternAnnouncementResponseModel

interface SearchScrapsRepository {
    suspend fun getSearchScrapsList(): Result<List<InternAnnouncementResponseModel>>
}