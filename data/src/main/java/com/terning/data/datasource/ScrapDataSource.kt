package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.ScrapRequestDto
import com.terning.data.dto.response.ScrapResponseDto
import com.terning.data.dto.response.ScrapResponsesDto

interface ScrapDataSource {
    suspend fun getScrapMonth(request: ScrapRequestDto): BaseResponse<List<ScrapResponsesDto>>
}