package com.terning.data.auth.mapper

import com.terning.data.auth.dto.request.SignInRequestDto
import com.terning.domain.auth.entity.SignInRequest

fun SignInRequest.toSignInRequestDto(): SignInRequestDto =
    SignInRequestDto(authType = authType)