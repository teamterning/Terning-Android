package com.terning.data.home.mapper

import com.terning.data.home.dto.request.FcmTokenRequestDto
import com.terning.domain.home.entity.FcmToken

fun FcmToken.toRequestDto(): FcmTokenRequestDto =
    FcmTokenRequestDto(
        fcmToken = fcmToken
    )