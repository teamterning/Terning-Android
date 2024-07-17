package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.SearchRequestDto
import com.terning.data.dto.response.SearchResultResponseDto
import com.terning.data.dto.response.SearchScrapsResponseDto
import com.terning.data.dto.response.SearchViewsResponseDto

interface SearchDataSource {
    suspend fun getSearch(request: SearchRequestDto): BaseResponse<SearchResultResponseDto>
    suspend fun getSearchViews(): BaseResponse<SearchViewsResponseDto>
    suspend fun getSearchScraps(): BaseResponse<SearchScrapsResponseDto>
}