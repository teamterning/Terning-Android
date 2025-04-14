package com.terning.domain.token.repository

interface TokenRepository {
    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun getFcmToken(): String

    fun setAccessToken(accessToken: String)

    fun setRefreshToken(refreshToken: String)

    fun setFcmToken(fcmToken: String)

    fun setUserId(userId: Long)

    fun getUserId(): Long

    fun clearInfo()
}
