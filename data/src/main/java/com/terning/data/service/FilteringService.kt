package com.terning.data.service

import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.FilteringRequestDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FilteringService {
    @POST("/api/v1/auth/sign-up/filter")
    suspend fun postFilteringService(
        @Header("userId") userId: Long,
        @Body request: FilteringRequestDto
    ): NonDataBaseResponse
}