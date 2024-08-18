package com.terning.domain.entity.onboarding

data class SignInResponse(
    val accessToken: String?,
    val refreshToken: String?,
    val userId: Long?,
    val authId: String,
    val authType: String
)