package com.terning.data.auth.datasource

import com.terning.core.network.BaseResponse
import com.terning.data.auth.dto.request.SignInRequestDto
import com.terning.data.auth.dto.request.SignUpRequestDto
import com.terning.data.auth.dto.response.SignInResponseDto
import com.terning.data.auth.dto.response.SignUpResponseDto

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