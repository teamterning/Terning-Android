package com.terning.data.auth.mapper

import com.terning.data.auth.dto.request.SignInRequestDto
import com.terning.domain.entity.onboarding.SignInRequest

fun SignInRequest.toSignInRequestDto(): SignInRequestDto =
    com.terning.data.auth.dto.request.SignInRequestDto(authType = authType)