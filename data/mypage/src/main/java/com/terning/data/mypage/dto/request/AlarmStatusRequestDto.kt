package com.terning.data.mypage.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlarmStatusRequestDto(
    @SerialName("newStatus")
    val newStatus: String
)