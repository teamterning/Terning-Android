package com.terning.data.mapper.auth

import com.terning.data.dto.request.SignUpRequestDto
import com.terning.domain.entity.auth.SignUpRequest

fun SignUpRequest.toSignUpRequestDto(): SignUpRequestDto =
    SignUpRequestDto(
        name = name,
        profileImage = profileImage,
        authType = authType
    )