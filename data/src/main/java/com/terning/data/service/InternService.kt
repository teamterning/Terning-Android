package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.RecommendInternResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface InternService {
    @GET("api/v1/home")
    suspend fun getRecommendIntern(
        @Query("sortBy") sortBy: String,
    ): BaseResponse<RecommendInternResponseDto>

}