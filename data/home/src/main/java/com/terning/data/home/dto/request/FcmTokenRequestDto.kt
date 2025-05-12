package com.terning.data.home.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FcmTokenRequestDto(
    @SerialName("fcmToken")
    val fcmToken: String
)