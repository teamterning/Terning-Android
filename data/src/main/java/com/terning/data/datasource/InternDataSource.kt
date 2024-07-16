package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.InternResponseDto

interface InternDataSource {
    suspend fun getInternInfo(id: Int): BaseResponse<InternResponseDto>
}