package com.terning.data.repositoryimpl

import com.terning.data.datasource.TokenReissueDataSource
import com.terning.data.mapper.auth.toTokenReissue
import com.terning.domain.entity.auth.TokenReissue
import com.terning.domain.repository.TokenReissueRepository
import javax.inject.Inject

class TokenReissueRepositoryImpl @Inject constructor(
    private val tokenReissueDataSource: TokenReissueDataSource
) : TokenReissueRepository {
    override suspend fun postReissueToken(
        authorization: String
    ): Result<TokenReissue> =
        runCatching {
            tokenReissueDataSource.postReissueToken(
                authorization = authorization
            ).result.toTokenReissue()
        }
}