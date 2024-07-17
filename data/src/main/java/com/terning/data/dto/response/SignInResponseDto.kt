package com.terning.data.dto.response

import com.terning.domain.entity.response.SignInResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    @SerialName("accessToken")
    val accessToken: String? = null,
    @SerialName("refreshToken")
    val refreshToken: String? = null,
    @SerialName("userId")
    val userId: Long? = null,
    @SerialName("authId")
    val authId: String,
    @SerialName("authType")
    val authType: String,
) {
    fun toSignInModel() = SignInResponseModel(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authId = authId,
        authType = authType
    )
}