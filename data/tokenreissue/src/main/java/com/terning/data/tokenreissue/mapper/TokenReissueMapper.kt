package com.terning.data.tokenreissue.mapper

import com.terning.data.tokenreissue.dto.response.TokenReissueResponseDto
import com.terning.domain.tokenreissue.entity.TokenReissue

fun TokenReissueResponseDto.toTokenReissue(): TokenReissue =
    TokenReissue(accessToken = accessToken)
