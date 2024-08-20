package com.terning.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenReissueResponseDto(
    @SerialName("refreshToken")
    val refreshToken: String
)