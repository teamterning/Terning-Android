package com.terning.domain.user.repository

interface UserRepository {
    fun getAccessToken(): String

    fun setAccessToken(accessToken: String)

    fun getRefreshToken(): String

    fun setRefreshToken(refreshToken: String)

    fun getFcmToken(): String

    suspend fun fetchAndSetFcmToken(): Result<Unit>

    fun getUserId(): Long

    fun setUserId(userId: Long)

    fun getAlarmAvailable(): Boolean

    fun setAlarmAvailable(availability: Boolean)

    fun setPermissionRequested(requested: Boolean)

    fun getPermissionRequested(): Boolean

    fun clearInfo()
}
