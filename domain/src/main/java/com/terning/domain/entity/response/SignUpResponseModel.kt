package com.terning.domain.entity.response

data class SignUpResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long,
    val authType: String
)