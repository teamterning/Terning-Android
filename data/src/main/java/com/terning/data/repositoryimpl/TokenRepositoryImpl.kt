package com.terning.data.repositoryimpl

import com.terning.data.datasource.TokenReissueDataSource
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenReissueDataSource : TokenReissueDataSource
) {
}