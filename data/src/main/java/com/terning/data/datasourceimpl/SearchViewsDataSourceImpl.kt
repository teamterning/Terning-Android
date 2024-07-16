package com.terning.data.datasourceimpl

import com.terning.data.datasource.SearchViewsDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.SearchViewsResponseDto
import com.terning.data.service.SearchService
import javax.inject.Inject

class SearchViewsDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchViewsDataSource {
    override suspend fun getSearchViews(): BaseResponse<SearchViewsResponseDto> =
        searchService.getSearchViewsList()
}