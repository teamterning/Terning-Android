package com.terning.data.mapper.mypage

import com.terning.data.dto.response.MyPageResponseDto
import com.terning.domain.entity.mypage.MyPageProfileModel

fun MyPageResponseDto.toMyPageProfile() = MyPageProfileModel(name = name, authType = authType)
