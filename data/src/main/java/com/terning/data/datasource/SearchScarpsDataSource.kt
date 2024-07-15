package com.terning.data.datasource

import com.terning.data.dto.response.SearchScarpsResponseDto

interface SearchScarpsDataSource {
    suspend fun getSearchScarps(): SearchScarpsResponseDto
}