package com.terning.data.auth.dto.request

import kotlinx.serialization.SerialName

@Serializable
data class SignUpRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("authType")
    val authType: String
)