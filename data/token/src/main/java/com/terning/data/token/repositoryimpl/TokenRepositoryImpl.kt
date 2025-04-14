package com.terning.data.token.repositoryimpl

import com.terning.core.local.TerningDataStore
import com.terning.domain.token.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val terningDataStore: TerningDataStore
) : TokenRepository {
    override fun getAccessToken(): String = terningDataStore.accessToken

    override fun getRefreshToken(): String = terningDataStore.refreshToken
    override fun getFcmToken(): String = terningDataStore.fcmToken

    override fun getUserId(): Long = terningDataStore.userId

    override fun setAccessToken(accessToken: String) {
        terningDataStore.accessToken = accessToken
    }

    override fun setRefreshToken(refreshToken: String) {
        terningDataStore.refreshToken = refreshToken
    }

    override fun setFcmToken(fcmToken: String) {
        terningDataStore.fcmToken = fcmToken
    }

    override fun setUserId(userId: Long) {
        terningDataStore.userId = userId
    }

    override fun clearInfo() {
        terningDataStore.clearInfo()
    }
}