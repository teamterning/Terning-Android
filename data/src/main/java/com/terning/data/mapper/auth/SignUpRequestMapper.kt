package com.terning.data.mapper.auth

import com.terning.data.dto.request.SignUpRequestDto
import com.terning.domain.entity.auth.SignUpRequestModel

fun SignUpRequestModel.toSignUpRequestDto(): SignUpRequestDto =
    SignUpRequestDto(
        name = name,
        profileImage = profileImage,
        authType = authType
    )