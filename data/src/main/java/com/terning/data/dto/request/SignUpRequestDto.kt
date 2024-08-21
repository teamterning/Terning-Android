package com.terning.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: Int,
    @SerialName("authType")
    val authType: String
)