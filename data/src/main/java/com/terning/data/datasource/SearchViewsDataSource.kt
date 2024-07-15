package com.terning.data.datasource

import com.terning.data.dto.response.InternAnnouncementResponseDto

interface SearchViewsDataSource {
    suspend fun getSearchViews(): InternAnnouncementResponseDto
}