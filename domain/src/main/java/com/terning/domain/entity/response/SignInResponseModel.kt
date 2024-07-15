package com.terning.domain.entity.response

data class SignInResponseModel(
    val accessToken : String? ,
    val refreshToken : String?,
    val userId : Long,
    val authType: String
)