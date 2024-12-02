package com.terning.data.auth.datasourceimpl

import com.terning.core.network.BaseResponse
import com.terning.data.auth.datasource.AuthDataSource
import com.terning.data.auth.dto.request.SignInRequestDto
import com.terning.data.auth.dto.request.SignUpRequestDto
import com.terning.data.auth.dto.response.SignInResponseDto
import com.terning.data.auth.dto.response.SignUpResponseDto
import com.terning.data.auth.service.AuthService
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