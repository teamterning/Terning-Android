package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchScrapsDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchScrapsResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchScrapsDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchScrapsDataSource {
    override suspend fun getSearchScraps(): BaseResponse<SearchScrapsResponseDto> =
        searchService.getSearchScrapsList()
}