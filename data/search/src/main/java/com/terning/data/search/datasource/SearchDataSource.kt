package com.terning.data.search.datasource

import com.terning.core.network.BaseResponse
import com.terning.data.search.dto.request.SearchRequestDto
import com.terning.data.search.dto.response.SearchAnnouncementResponseDto
import com.terning.data.search.dto.response.SearchResultResponseDto

interface SearchDataSource {
    suspend fun getSearch(request: SearchRequestDto): BaseResponse<SearchResultResponseDto>
    suspend fun getSearchViews(): BaseResponse<SearchAnnouncementResponseDto>
    suspend fun getSearchScraps(): BaseResponse<SearchAnnouncementResponseDto>
}