package com.terning.data.mapper.auth

import com.terning.data.dto.response.SignUpResponseDto
import com.terning.domain.entity.auth.SignUpResponse

fun SignUpResponseDto.toSignUpResponse(): SignUpResponse =
    SignUpResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authType = authType
    )
