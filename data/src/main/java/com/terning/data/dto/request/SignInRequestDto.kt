package com.terning.data.dto.request

import com.terning.domain.entity.request.SignInRequestModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    @SerialName("authType")
    val authType: String
)

fun SignInRequestModel.toSignInRequestDto(): SignInRequestDto =
    SignInRequestDto(authType = authType)