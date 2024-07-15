package com.terning.data.datasourceimpl

import com.terning.data.datasource.InternDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.RecommendInternResponseDto
import com.terning.data.service.InternService
import javax.inject.Inject

class InternDataSourceImpl @Inject constructor(
    private val internService: InternService
) : InternDataSource {
    override suspend fun getRecommendIntern(sortBy: String): BaseResponse<RecommendInternResponseDto> =
        internService.getRecommendIntern(sortBy = sortBy)
}