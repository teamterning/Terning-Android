package com.terning.data.auth.dto.response

import kotlinx.serialization.SerialName

@Serializable
data class SignInResponseDto(
    @SerialName("accessToken")
    val accessToken: String?,
    @SerialName("refreshToken")
    val refreshToken: String?,
    @SerialName("userId")
    val userId: Long?,
    @SerialName("authId")
    val authId: String,
    @SerialName("authType")
    val authType: String,
)