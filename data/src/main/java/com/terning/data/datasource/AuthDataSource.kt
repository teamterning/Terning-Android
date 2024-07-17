package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.SignInRequestDto
import com.terning.data.dto.request.SignUpRequestDto
import com.terning.data.dto.response.SignInResponseDto
import com.terning.data.dto.response.SignUpResponseDto

interface AuthDataSource {

    suspend fun postSignIn(
        authorization: String,
        request: SignInRequestDto
    ): BaseResponse<SignInResponseDto>

    suspend fun postSignUp(
        authId: String,
        request: SignUpRequestDto
    ): BaseResponse<SignUpResponseDto>

}