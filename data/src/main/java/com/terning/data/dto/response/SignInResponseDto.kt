package com.terning.data.dto.response

import com.terning.domain.entity.response.SignInResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    @SerialName("accessToken")
    val platform: String,
    @SerialName("id")
    val id: Long,
    @SerialName("createdAt")
    val createdAt: String,
//    @SerialName("authType")
//    val authType: String
) {
    fun toSignInModel() = SignInResponseModel(platform, id, createdAt, )
        //authType)
}