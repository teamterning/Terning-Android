package com.terning.data.filtering.datasourceimpl

import com.terning.core.network.NonDataBaseResponse
import com.terning.data.filtering.dto.request.FilteringRequestDto
import com.terning.data.filtering.service.FilteringService
import javax.inject.Inject

class FilteringDataSourceImpl @Inject constructor(
    private val filteringService: FilteringService
) : com.terning.data.filtering.datasource.FilteringDataSource {
    override suspend fun postFiltering(
        userId: Long,
        request: FilteringRequestDto
    ): NonDataBaseResponse =
        filteringService.postFilteringService(userId, request)
}