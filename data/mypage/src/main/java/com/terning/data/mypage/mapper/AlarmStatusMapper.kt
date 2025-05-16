package com.terning.data.mypage.mapper

import com.terning.data.mypage.dto.request.AlarmStatusRequestDto
import com.terning.domain.mypage.entity.AlarmStatus

fun AlarmStatus.toAlarmStatusRequestDto(): AlarmStatusRequestDto =
    AlarmStatusRequestDto(
        newStatus = newStatus
    )