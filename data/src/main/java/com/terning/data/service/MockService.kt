package com.terning.data.service

import com.terning.data.dto.response.MockResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MockService {
    @GET("api/users")
    suspend fun getMockListFromServer(
        @Query("page") page: Int,
    ): MockResponseDto
}