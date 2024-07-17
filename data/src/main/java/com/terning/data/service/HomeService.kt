package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.HomeTodayInternResponseDto
import retrofit2.http.GET

interface HomeService {
    @GET("api/v1/home/today")
    suspend fun getHomeTodayIntern(): BaseResponse<List<HomeTodayInternResponseDto>>
}
