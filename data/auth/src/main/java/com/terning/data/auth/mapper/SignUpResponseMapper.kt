package com.terning.data.auth.mapper

import com.terning.data.auth.dto.response.SignUpResponseDto
import com.terning.domain.auth.entity.SignUpResponse

fun SignUpResponseDto.toSignUpResponse(): SignUpResponse =
    SignUpResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authType = authType
    )
