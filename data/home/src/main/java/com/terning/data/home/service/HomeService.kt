package com.terning.data.home.service

import com.terning.core.network.BaseResponse
import com.terning.core.network.NonDataBaseResponse
import com.terning.data.home.dto.request.ChangeFilterRequestDto
import com.terning.data.home.dto.request.FcmTokenRequestDto
import com.terning.data.home.dto.response.HomeFilteringInfoResponseDto
import com.terning.data.home.dto.response.HomeRecommendInternResponseDto
import com.terning.data.home.dto.response.HomeUpcomingInternResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface HomeService {
    @GET("api/v1/home/upcoming")
    suspend fun getHomeUpcomingIntern(): BaseResponse<HomeUpcomingInternResponseDto>

    @GET("api/v1/home")
    suspend fun getRecommendIntern(
        @Query("sortBy") sortBy: String,
        @Query("page") page: Int,
    ): BaseResponse<HomeRecommendInternResponseDto>

    @GET("api/v1/filters")
    suspend fun getFilteringInfo(): BaseResponse<HomeFilteringInfoResponseDto>

    @PUT("api/v1/filters")
    suspend fun putFilteringInfo(
        @Body body: ChangeFilterRequestDto,
    ): NonDataBaseResponse

    @POST("api/v1/auth/sync-user")
    suspend fun sendFcmToken(@Body body: FcmTokenRequestDto): NonDataBaseResponse
}