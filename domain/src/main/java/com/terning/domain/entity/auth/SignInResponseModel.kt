package com.terning.domain.entity.auth

data class SignInResponseModel(
    val accessToken: String?,
    val refreshToken: String?,
    val userId: Long?,
    val authId: String,
    val authType: String
)