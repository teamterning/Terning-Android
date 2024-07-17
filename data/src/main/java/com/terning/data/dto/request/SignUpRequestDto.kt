package com.terning.data.dto.request

import com.terning.domain.entity.request.SignUpRequestModel
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

fun SignUpRequestModel.toSignUpRequestDto(): SignUpRequestDto =
    SignUpRequestDto(
        name = name,
        profileImage = profileImage,
        authType = authType
    )