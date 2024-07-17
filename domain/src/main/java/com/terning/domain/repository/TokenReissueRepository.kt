package com.terning.domain.repository

import com.terning.domain.entity.response.TokenReissueResponseModel

interface TokenReissueRepository {
    suspend fun postReissueToken(
        authorization: String,
    ): Result<TokenReissueResponseModel>
}