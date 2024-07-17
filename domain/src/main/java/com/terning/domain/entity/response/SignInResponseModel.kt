package com.terning.domain.entity.response

data class SignInResponseModel(
    val accessToken: String? =null,
    val refreshToken: String?=null,
    val userId: Long?=null,
    val authId: String,
    val authType: String
)