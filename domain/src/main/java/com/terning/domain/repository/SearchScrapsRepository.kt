package com.terning.domain.repository

import com.terning.domain.entity.response.InternScrapsResponseModel

interface SearchScrapsRepository {
    suspend fun getSearchScrapsList(): Result<List<InternScrapsResponseModel>>
}