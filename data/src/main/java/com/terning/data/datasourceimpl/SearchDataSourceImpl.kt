package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.SearchRequestDto
import com.terning.data.dto.response.SearchResultResponseDto
import com.terning.data.dto.response.SearchAnnouncementResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchDataSource {

    override suspend fun getSearch(request: SearchRequestDto):
            BaseResponse<SearchResultResponseDto> =
        searchService.getSearch(
            request.keyword,
            request.sortBy,
            request.page,
            request.size
        )

    override suspend fun getSearchViews(): BaseResponse<SearchAnnouncementResponseDto> =
        searchService.getSearchViewsList()

    override suspend fun getSearchScraps(): BaseResponse<SearchAnnouncementResponseDto> =
        searchService.getSearchScrapsList()
}