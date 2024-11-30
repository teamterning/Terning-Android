package com.terning.data.auth.service

import com.terning.core.network.BaseResponse
import com.terning.data.auth.dto.response.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("api/v1/auth/sign-in")
    suspend fun postSignIn(
        @Header("Authorization") authorization: String,
        @Body body: com.terning.data.auth.dto.request.SignInRequestDto,
    ): BaseResponse<SignInResponseDto>

    @POST("api/v1/auth/sign-up")
    suspend fun postSignUp(
        @Header("Authorization") authId: String,
        @Body body: com.terning.data.auth.dto.request.SignUpRequestDto,
    ): BaseResponse<com.terning.data.auth.dto.response.SignUpResponseDto>
}