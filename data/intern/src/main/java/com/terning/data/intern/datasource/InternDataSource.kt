package com.terning.data.intern.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.InternResponseDto

interface InternDataSource {
    suspend fun getInternInfo(id: Long): BaseResponse<InternResponseDto>
}