package com.terning.domain.repository

import com.terning.domain.entity.response.InternAnnouncementResponseModel

interface SearchViewsRepository {
    suspend fun getSearchViewsList(): Result<List<InternAnnouncementResponseModel>>
}