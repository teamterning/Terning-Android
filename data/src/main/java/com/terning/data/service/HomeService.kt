package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.HomeRecommendInternResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("api/v1/home")
    suspend fun getRecommendIntern(
        @Query("sortBy") sortBy: String,
        @Query("year") year: Int,
        @Query("month") month: Int,
    ): BaseResponse<List<HomeRecommendInternResponseDto>>
}