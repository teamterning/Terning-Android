package com.terning.data.mypage.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyPageResponseDto(
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String,
    @SerialName("authType")
    val authType: String
)