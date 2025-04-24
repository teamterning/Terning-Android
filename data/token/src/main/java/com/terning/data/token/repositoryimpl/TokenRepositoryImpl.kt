package com.terning.data.token.repositoryimpl

import com.terning.core.firebase.fcmtoken.FcmTokenProvider
import com.terning.core.local.TerningDataStore
import com.terning.domain.token.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val terningDataStore: TerningDataStore,
    private val fcmTokenProvider: FcmTokenProvider
) : TokenRepository {
    override fun getAccessToken(): String = terningDataStore.accessToken

    override fun setAccessToken(accessToken: String) {
        terningDataStore.accessToken = accessToken
    }

    override fun getRefreshToken(): String = terningDataStore.refreshToken

    override fun setRefreshToken(refreshToken: String) {
        terningDataStore.refreshToken = refreshToken
    }

    override fun getFcmToken(): String = terningDataStore.fcmToken

    override suspend fun fetchAndSetFcmToken(): Result<Unit> = runCatching {
        terningDataStore.fcmToken = fcmTokenProvider.fetchFcmToken()
    }

    override fun getUserId(): Long = terningDataStore.userId

    override fun setUserId(userId: Long) {
        terningDataStore.userId = userId
    }

    override fun getAlarmAvailable(): Boolean = terningDataStore.alarmAvailable

    override fun setAlarmAvailable(availability: Boolean) {
        terningDataStore.alarmAvailable = availability
    }

    override fun setPermissionRequested(requested: Boolean) {
        terningDataStore.hasRequestedPermission = requested
    }

    override fun getPermissionRequested(): Boolean = terningDataStore.hasRequestedPermission

    override fun clearInfo() {
        terningDataStore.clearInfo()
    }
}