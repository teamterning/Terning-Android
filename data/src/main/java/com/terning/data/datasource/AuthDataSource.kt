package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.SignInRequestDto
import com.terning.data.dto.response.SignInResponseDto

interface AuthDataSource {
    suspend fun postSignIn(
        authorization: String,
        platform: SignInRequestDto
    ): BaseResponse<SignInResponseDto>
}