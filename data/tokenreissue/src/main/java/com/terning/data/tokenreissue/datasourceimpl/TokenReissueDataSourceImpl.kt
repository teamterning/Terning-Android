package com.terning.data.datasourceimpl

import com.terning.data.datasource.TokenReissueDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.response.TokenReissueResponseDto
import com.terning.data.service.TokenReissueService
import javax.inject.Inject

class TokenReissueDataSourceImpl @Inject constructor(
    private val tokenReissueService: TokenReissueService
) : TokenReissueDataSource {
    override suspend fun postReissueToken(
        authorization: String
    ): BaseResponse<TokenReissueResponseDto> =
        tokenReissueService.postReissueToken("Bearer $authorization")
}