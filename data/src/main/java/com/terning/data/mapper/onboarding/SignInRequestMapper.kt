package com.terning.data.mapper.onboarding

import com.terning.data.dto.request.SignInRequestDto
import com.terning.domain.entity.onboarding.SignInRequest

fun SignInRequest.toSignInRequestDto(): SignInRequestDto =
    SignInRequestDto(authType = authType)