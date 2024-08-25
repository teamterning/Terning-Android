package com.terning.data.mapper.onboarding

import com.terning.data.dto.response.SignInResponseDto
import com.terning.domain.entity.onboarding.SignInResponse

fun SignInResponseDto.toSignInResponse(): SignInResponse =
    SignInResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authId = authId,
        authType = authType
    )
