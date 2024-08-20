package com.terning.data.mapper.onboarding

import com.terning.data.dto.request.SignUpRequestDto
import com.terning.domain.entity.onboarding.SignUpRequest

fun SignUpRequest.toSignUpRequestDto(): SignUpRequestDto =
    SignUpRequestDto(
        name = name,
        profileImage = profileImage,
        authType = authType
    )