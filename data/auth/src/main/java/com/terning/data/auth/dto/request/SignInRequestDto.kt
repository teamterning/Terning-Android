package com.terning.data.auth.dto.request

import kotlinx.serialization.SerialName

@Serializable
data class SignInRequestDto(
    @SerialName("authType")
    val authType: String
)