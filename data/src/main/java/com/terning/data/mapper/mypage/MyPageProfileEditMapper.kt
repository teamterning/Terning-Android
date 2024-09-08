package com.terning.data.mapper.mypage

import com.terning.data.dto.request.MyPageProfileEditRequestDto
import com.terning.domain.entity.mypage.MyPageProfileEdit

fun MyPageProfileEdit.toMyPageProfileEditRequestDto(): MyPageProfileEditRequestDto =
    MyPageProfileEditRequestDto(
        name = name,
        profileImage = profileImage
    )
