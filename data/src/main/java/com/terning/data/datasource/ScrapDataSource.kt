package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.ScrapRequestDto
import com.terning.data.dto.response.ScrapResponseDto

interface ScrapDataSource {
    suspend fun getMonthScrapList(request: ScrapRequestDto): BaseResponse<ScrapResponseDto>
}