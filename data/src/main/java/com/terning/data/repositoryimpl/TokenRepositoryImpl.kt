package com.terning.data.repositoryimpl

import com.terning.data.local.TerningDataStore
import com.terning.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val terningDataStore: TerningDataStore
) : TokenRepository {
    override fun getAccessToken(): String = terningDataStore.accessToken

    override fun getRefreshToken(): String = terningDataStore.refreshToken

    override fun setTokens(accessToken: String, refreshToken: String) {
        terningDataStore.accessToken = accessToken
        terningDataStore.refreshToken = refreshToken
    }

    override fun setUserId(userId: Long) {
        terningDataStore.userId = userId
    }

    override fun clearInfo() {
        terningDataStore.clearInfo()
    }

}