package com.terning.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    @SerialName("authType")
    val authType: String
)