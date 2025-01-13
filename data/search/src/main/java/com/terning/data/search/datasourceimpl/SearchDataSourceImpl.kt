package com.terning.data.search.datasourceimpl

import com.terning.core.network.BaseResponse
import com.terning.data.search.datasource.SearchDataSource
import com.terning.data.search.dto.request.SearchRequestDto
import com.terning.data.search.dto.response.SearchAnnouncementResponseDto
import com.terning.data.search.dto.response.SearchBannersResponseDto
import com.terning.data.search.dto.response.SearchResultResponseDto
import com.terning.data.search.service.SearchService
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
        )

    override suspend fun getSearchViews(): BaseResponse<SearchAnnouncementResponseDto> =
        searchService.getSearchViewsList()

    override suspend fun getSearchScraps(): BaseResponse<SearchAnnouncementResponseDto> =
        searchService.getSearchScrapsList()

    override suspend fun getSearchBanners(): BaseResponse<SearchBannersResponseDto> =
        searchService.getSearchBannerList()
}