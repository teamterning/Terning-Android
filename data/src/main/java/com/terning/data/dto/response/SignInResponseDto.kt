package com.terning.data.dto.response

import com.terning.domain.entity.response.SignInResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("userId")
    val userId: Long,
    @SerialName("authType")
    val authType: String,
) {
    fun toSignInModel() = SignInResponseModel(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authType = authType
    )
}