package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.TokenReissueResponseDto

interface TokenReissueDataSource {
    suspend fun postReissueToken(
        authorization: String,
    ): BaseResponse<TokenReissueResponseDto>
}