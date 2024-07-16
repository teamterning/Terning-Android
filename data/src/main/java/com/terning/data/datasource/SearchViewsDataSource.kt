package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchViewsResponseDto

interface SearchViewsDataSource {
    suspend fun getSearchViews(): BaseResponse<SearchViewsResponseDto>
}