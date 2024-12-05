package com.terning.data.filtering.datasource

import com.terning.core.network.NonDataBaseResponse
import com.terning.data.filtering.dto.request.FilteringRequestDto

interface FilteringDataSource {
    suspend fun postFiltering(userId: Long, request: FilteringRequestDto): NonDataBaseResponse
}