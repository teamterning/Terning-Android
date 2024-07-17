package com.terning.data.dto.response

import com.terning.domain.entity.response.SignUpResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("userId")
    val userId: Long,
    @SerialName("authType")
    val authType: String,
) {
    fun toSignUpModel() = SignUpResponseModel(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authType = authType
    )
}