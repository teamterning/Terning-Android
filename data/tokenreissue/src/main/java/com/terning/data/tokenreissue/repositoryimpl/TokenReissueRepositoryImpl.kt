package com.terning.data.tokenreissue.repositoryimpl

import com.terning.data.datasource.TokenReissueDataSource
import com.terning.data.tokenreissue.mapper.toTokenReissue
import com.terning.domain.tokenreissue.entity.TokenReissue
import com.terning.domain.tokenreissue.repository.TokenReissueRepository
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