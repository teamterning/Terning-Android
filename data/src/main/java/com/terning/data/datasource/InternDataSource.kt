package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.RecommendInternResponseDto

interface InternDataSource {
    suspend fun getRecommendIntern(sortBy: String): BaseResponse<RecommendInternResponseDto>
}