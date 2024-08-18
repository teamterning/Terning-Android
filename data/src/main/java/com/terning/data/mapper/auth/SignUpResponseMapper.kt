package com.terning.data.mapper.auth

import com.terning.data.dto.response.SignUpResponseDto
import com.terning.domain.entity.auth.SignUpResponseModel

fun SignUpResponseDto.toSignUpModel(): SignUpResponseModel =
    SignUpResponseModel(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authType = authType
    )
