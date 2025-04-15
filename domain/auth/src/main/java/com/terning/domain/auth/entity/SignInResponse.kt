package com.terning.domain.auth.entity

data class SignInResponse(
    val accessToken: String?,
    val refreshToken: String?,
    val userId: Long?,
    val authId: String,
    val authType: String,
   // val fcmTokenReissueRequired: Boolean
)