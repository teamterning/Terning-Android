package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchScrapsResponseDto

interface SearchScrapsDataSource {
    suspend fun getSearchScraps(): BaseResponse<SearchScrapsResponseDto>
}