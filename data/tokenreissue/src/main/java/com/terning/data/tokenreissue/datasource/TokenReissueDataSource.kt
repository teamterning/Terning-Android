package com.terning.data.datasource

import com.terning.core.network.BaseResponse
import com.terning.data.tokenreissue.dto.response.TokenReissueResponseDto

interface TokenReissueDataSource {
    suspend fun postReissueToken(
        authorization: String,
    ): BaseResponse<TokenReissueResponseDto>
}