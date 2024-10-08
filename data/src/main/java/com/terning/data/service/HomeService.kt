package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.ChangeFilterRequestDto
import com.terning.data.dto.response.HomeFilteringInfoResponseDto
import com.terning.data.dto.response.HomeRecommendInternResponseDto
import com.terning.data.dto.response.HomeUpcomingInternResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface HomeService {
    @GET("api/v1/home/upcoming")
    suspend fun getHomeUpcomingIntern(): BaseResponse<HomeUpcomingInternResponseDto>

    @GET("api/v1/home")
    suspend fun getRecommendIntern(
        @Query("sortBy") sortBy: String,
        @Query("startYear") startYear: Int,
        @Query("startMonth") startMonth: Int,
    ): BaseResponse<HomeRecommendInternResponseDto>

    @GET("api/v1/filters")
    suspend fun getFilteringInfo(): BaseResponse<HomeFilteringInfoResponseDto>

    @PUT("api/v1/filters")
    suspend fun putFilteringInfo(
        @Body body: ChangeFilterRequestDto,
    ): NonDataBaseResponse
}