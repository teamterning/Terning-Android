package com.terning.data.auth.mapper

import com.terning.data.auth.dto.request.SignUpRequestDto
import com.terning.domain.auth.entity.SignUpRequest

fun SignUpRequest.toSignUpRequestDto(): SignUpRequestDto =
    SignUpRequestDto(
        name = name,
        profileImage = profileImage,
        authType = authType,
        fcmToken = fcmToken
    )