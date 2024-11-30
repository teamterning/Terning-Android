package com.terning.data.tokenreissue.service

import com.terning.core.network.BaseResponse
import com.terning.data.tokenreissue.dto.response.TokenReissueResponseDto
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenReissueService {
    @POST("/api/v1/auth/token-reissue")
    suspend fun postReissueToken(
        @Header("Authorization") authorization: String,
    ): BaseResponse<TokenReissueResponseDto>
}