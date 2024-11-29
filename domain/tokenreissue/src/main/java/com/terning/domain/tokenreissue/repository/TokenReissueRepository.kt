package com.terning.domain.tokenreissue.repository

import com.terning.domain.tokenreissue.entity.TokenReissue

interface TokenReissueRepository {
    suspend fun postReissueToken(
        authorization: String,
    ): Result<TokenReissue>
}