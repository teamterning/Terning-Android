package com.terning.data.datasource

import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.FilteringRequestDto

interface FilteringDataSource {
    suspend fun postFiltering(userId: Long, request: FilteringRequestDto): NonDataBaseResponse
}