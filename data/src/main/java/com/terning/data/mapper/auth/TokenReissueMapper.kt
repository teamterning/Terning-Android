package com.terning.data.mapper.auth

import com.terning.data.dto.response.TokenReissueResponseDto
import com.terning.domain.entity.auth.TokenReissue

fun TokenReissueResponseDto.toTokenReissue(): TokenReissue =
    TokenReissue(refreshToken = refreshToken)
