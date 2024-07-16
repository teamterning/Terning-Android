package com.terning.domain.repository

interface TokenRepository {
    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun setTokens(accessToken: String, refreshToken: String)

    fun setUserId(userId: Long)

    fun getUserId(): Long

    fun clearInfo()
}
