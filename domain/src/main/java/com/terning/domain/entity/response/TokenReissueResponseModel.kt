package com.terning.domain.entity.response

data class TokenReissueResponseModel (
    val accessToken : String,
    val refreshToken : String
)