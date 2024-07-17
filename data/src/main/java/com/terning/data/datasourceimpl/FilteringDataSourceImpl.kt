package com.terning.data.datasourceimpl

import com.terning.data.datasource.FilteringDataSource
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.FilteringRequestDto
import com.terning.data.service.FilteringService
import javax.inject.Inject

class FilteringDataSourceImpl @Inject constructor(
    private val filteringService: FilteringService
) : FilteringDataSource {
    override suspend fun postFiltering(
        userId: Long,
        request: FilteringRequestDto
    ): NonDataBaseResponse =
        filteringService.postFilteringService(userId, request)
}