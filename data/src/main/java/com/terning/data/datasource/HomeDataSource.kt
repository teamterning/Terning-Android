package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.HomeRecommendInternResponseDto

interface HomeDataSource {
    suspend fun getRecommendIntern(sortBy: String): BaseResponse<HomeRecommendInternResponseDto>
}