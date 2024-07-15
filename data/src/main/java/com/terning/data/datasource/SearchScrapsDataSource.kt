package com.terning.data.datasource

import com.terning.data.dto.response.SearchScrapsResponseDto

interface SearchScrapsDataSource {
    suspend fun getSearchScraps(): SearchScrapsResponseDto
}