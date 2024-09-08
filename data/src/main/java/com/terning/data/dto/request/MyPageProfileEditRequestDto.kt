package com.terning.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyPageProfileEditRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String,
)