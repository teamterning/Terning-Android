package com.terning.data.intern.datasourceimpl

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.InternResponseDto
import com.terning.data.service.InternService
import javax.inject.Inject

class InternDataSourceImpl @Inject constructor(
    private val internService: InternService,
) : com.terning.data.intern.datasource.InternDataSource {
    override suspend fun getInternInfo(id: Long): BaseResponse<InternResponseDto> =
        internService.getInternInfo(id)
}