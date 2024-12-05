package com.terning.data.mypage.mapper

import com.terning.data.mypage.dto.request.MyPageProfileEditRequestDto
import com.terning.domain.mypage.entity.MyPageProfileEdit

fun MyPageProfileEdit.toMyPageProfileEditRequestDto(): MyPageProfileEditRequestDto =
    MyPageProfileEditRequestDto(
        name = name,
        profileImage = profileImage
    )
