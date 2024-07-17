package com.terning.data.dto.response

import com.terning.domain.entity.response.MyPageProfileModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyPageResponseDto(
    @SerialName("name")
    val name: String,
    @SerialName("authType")
    val authType: String
)

fun MyPageResponseDto.toMyPageProfileModel() = MyPageProfileModel(name = name, authType = authType)
