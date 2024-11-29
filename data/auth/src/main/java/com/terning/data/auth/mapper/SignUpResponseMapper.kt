package com.terning.data.auth.mapper

import com.terning.domain.entity.onboarding.SignUpResponse

fun com.terning.data.auth.dto.response.SignUpResponseDto.toSignUpResponse(): SignUpResponse =
    SignUpResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authType = authType
    )
