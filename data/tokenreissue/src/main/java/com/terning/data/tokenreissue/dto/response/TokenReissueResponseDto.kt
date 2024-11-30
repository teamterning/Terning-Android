package com.terning.data.tokenreissue.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenReissueResponseDto(
    @SerialName("accessToken")
    val accessToken: String
)