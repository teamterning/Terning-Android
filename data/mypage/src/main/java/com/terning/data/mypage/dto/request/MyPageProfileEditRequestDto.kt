package com.terning.data.mypage.dto.request

import kotlinx.serialization.SerialName

@Serializable
data class MyPageProfileEditRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String,
)