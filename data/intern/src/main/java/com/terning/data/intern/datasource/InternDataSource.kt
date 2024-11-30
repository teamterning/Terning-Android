package com.terning.data.intern.datasource

import com.terning.core.network.BaseResponse
import com.terning.data.intern.dto.response.InternResponseDto

interface InternDataSource {
    suspend fun getInternInfo(id: Long): BaseResponse<InternResponseDto>
}