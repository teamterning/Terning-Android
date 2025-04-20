package com.terning.data.auth.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("authType")
    val authType: String,
    @SerialName("fcmToken")
    val fcmToken: String
)