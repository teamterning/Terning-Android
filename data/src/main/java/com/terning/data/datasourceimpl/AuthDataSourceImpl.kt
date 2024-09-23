package com.terning.data.datasourceimpl

import com.terning.data.datasource.AuthDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.SignInRequestDto
import com.terning.data.dto.request.SignUpRequestDto
import com.terning.data.dto.response.SignInResponseDto
import com.terning.data.dto.response.SignUpResponseDto
import com.terning.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {

    override suspend fun postSignIn(
        authorization: String,
        request: SignInRequestDto
    ): BaseResponse<SignInResponseDto> = authService.postSignIn("Bearer $authorization", request)

    override suspend fun postSignUp(
        authId: String,
        request: SignUpRequestDto
    ): BaseResponse<SignUpResponseDto> = authService.postSignUp("Bearer $authId", request)
}