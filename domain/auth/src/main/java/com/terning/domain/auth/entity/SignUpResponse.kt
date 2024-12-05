package com.terning.domain.auth.entity

data class SignUpResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long,
    val authType: String
)