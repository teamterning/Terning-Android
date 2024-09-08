package com.terning.data.mapper.mypage

import com.terning.data.dto.response.MyPageResponseDto
import com.terning.domain.entity.mypage.MyPageProfile

fun MyPageResponseDto.toMyPageProfile() =
    MyPageProfile(
        name = name,
        profileImage = profileImage,
        authType = authType
    )
