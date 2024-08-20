package com.terning.domain.repository

import com.terning.domain.entity.auth.TokenReissue

interface TokenReissueRepository {
    suspend fun postReissueToken(
        authorization: String,
    ): Result<TokenReissue>
}