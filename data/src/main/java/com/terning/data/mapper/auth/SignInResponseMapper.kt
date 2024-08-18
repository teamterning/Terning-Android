package com.terning.data.mapper.auth

import com.terning.data.dto.response.SignInResponseDto
import com.terning.domain.entity.auth.SignInResponseModel

fun SignInResponseDto.toSignInModel(): SignInResponseModel =
    SignInResponseModel(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authId = authId,
        authType = authType
    )
