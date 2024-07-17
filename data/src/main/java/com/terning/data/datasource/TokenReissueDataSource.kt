package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.TokenReissueResponseDto
import com.terning.domain.entity.response.TokenReissueResponseModel

interface TokenReissueDataSource {
    suspend fun postReissueToken(
        authorization: String,
    ): BaseResponse<TokenReissueResponseDto>
}