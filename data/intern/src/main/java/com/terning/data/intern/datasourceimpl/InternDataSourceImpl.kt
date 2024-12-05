package com.terning.data.intern.datasourceimpl

import com.terning.core.network.BaseResponse
import com.terning.data.intern.datasource.InternDataSource
import com.terning.data.intern.dto.response.InternResponseDto
import com.terning.data.intern.service.InternService
import javax.inject.Inject

class InternDataSourceImpl @Inject constructor(
    private val internService: InternService,
) : InternDataSource {
    override suspend fun getInternInfo(id: Long): BaseResponse<InternResponseDto> =
        internService.getInternInfo(id)
}