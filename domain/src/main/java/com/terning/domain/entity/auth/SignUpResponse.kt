package com.terning.domain.entity.auth

data class SignUpResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long,
    val authType: String
)