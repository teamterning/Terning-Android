package com.terning.data.auth.mapper

import com.terning.data.auth.dto.response.SignInResponseDto
import com.terning.domain.auth.entity.SignInResponse

fun SignInResponseDto.toSignInResponse(): SignInResponse =
    SignInResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId,
        authId = authId,
        authType = authType
    )
