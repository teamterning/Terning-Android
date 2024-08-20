package com.terning.data.mapper.onboarding

import com.terning.data.dto.response.SignUpResponseDto
import com.terning.domain.entity.onboarding.SignUpResponse

fun SignUpResponseDto.toSignUpResponse(): SignUpResponse =
    SignUpResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authType = authType
    )
