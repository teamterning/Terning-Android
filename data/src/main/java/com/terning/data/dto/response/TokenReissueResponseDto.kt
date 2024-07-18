package com.terning.data.dto.response

import com.terning.domain.entity.response.TokenReissueResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenReissueResponseDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
) {
    fun toTokenReissueResponseModel() =
        TokenReissueResponseModel(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
}