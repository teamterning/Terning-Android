package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.HomeRecommendInternResponseDto
import com.terning.data.dto.response.HomeTodayInternResponseDto

interface HomeDataSource {
    suspend fun getTodayIntern(): BaseResponse<List<HomeTodayInternResponseDto>>

    suspend fun getRecommendIntern(
        sortBy: String,
        startYear: Int,
        startMonth: Int
    ): BaseResponse<List<HomeRecommendInternResponseDto>>
}