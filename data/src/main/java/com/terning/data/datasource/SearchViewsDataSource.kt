package com.terning.data.datasource

import com.terning.data.dto.response.SearchViewsResponseDto

interface SearchViewsDataSource {
    suspend fun getSearchViews(): SearchViewsResponseDto
}