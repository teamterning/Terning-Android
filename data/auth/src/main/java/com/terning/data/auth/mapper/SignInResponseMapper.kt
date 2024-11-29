package com.terning.data.auth.mapper

import com.terning.domain.entity.onboarding.SignInResponse

fun SignInResponseDto.toSignInResponse(): SignInResponse =
    SignInResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authId = authId,
        authType = authType
    )
