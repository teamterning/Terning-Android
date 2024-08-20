package com.terning.domain.entity.onboarding

data class SignUpResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long,
    val authType: String
)