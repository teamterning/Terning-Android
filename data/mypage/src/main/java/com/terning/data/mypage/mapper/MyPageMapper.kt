package com.terning.data.mypage.mapper

import com.terning.domain.entity.mypage.MyPageProfile

fun com.terning.data.mypage.dto.response.MyPageResponseDto.toMyPageProfile() =
    MyPageProfile(
        name = name,
        profileImage = profileImage,
        authType = authType
    )
