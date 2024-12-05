package com.terning.data.filtering.service

import com.terning.core.network.NonDataBaseResponse
import com.terning.data.filtering.dto.request.FilteringRequestDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FilteringService {
    @POST("/api/v1/auth/sign-up/filter")
    suspend fun postFilteringService(
        @Header("User-Id") userId: Long,
        @Body request: FilteringRequestDto
    ): NonDataBaseResponse
}