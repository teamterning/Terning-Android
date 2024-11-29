package com.terning.data.search.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.SearchRequestDto
import com.terning.data.dto.response.SearchResultResponseDto
import com.terning.data.dto.response.SearchAnnouncementResponseDto

interface SearchDataSource {
    suspend fun getSearch(request: SearchRequestDto): BaseResponse<SearchResultResponseDto>
    suspend fun getSearchViews(): BaseResponse<SearchAnnouncementResponseDto>
    suspend fun getSearchScraps(): BaseResponse<SearchAnnouncementResponseDto>
}