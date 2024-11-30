package com.terning.data.mypage.mapper

import com.terning.data.mypage.dto.response.MyPageResponseDto
import com.terning.domain.mypage.entity.MyPageProfile

fun MyPageResponseDto.toMyPageProfile() =
    MyPageProfile(
        name = name,
        profileImage = profileImage,
        authType = authType
    )
