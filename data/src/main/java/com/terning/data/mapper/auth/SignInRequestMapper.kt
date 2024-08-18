package com.terning.data.mapper.auth

import com.terning.data.dto.request.SignInRequestDto
import com.terning.domain.entity.auth.SignInRequestModel

fun SignInRequestModel.toSignInRequestDto(): SignInRequestDto =
    SignInRequestDto(authType = authType)