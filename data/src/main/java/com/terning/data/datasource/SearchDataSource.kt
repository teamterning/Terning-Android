package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchViewsResponseDto

interface SearchDataSource {
    suspend fun getSearchViews(): BaseResponse<SearchViewsResponseDto>
}