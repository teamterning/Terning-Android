package com.terning.data.mypage.mapper

import com.terning.data.mypage.dto.request.MyPageProfileEditRequestDto
import com.terning.domain.entity.mypage.MyPageProfileEdit

fun MyPageProfileEdit.toMyPageProfileEditRequestDto(): com.terning.data.mypage.dto.request.MyPageProfileEditRequestDto =
    com.terning.data.mypage.dto.request.MyPageProfileEditRequestDto(
        name = name,
        profileImage = profileImage
    )
