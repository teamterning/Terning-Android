package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.SignInRequestDto
import com.terning.data.dto.request.SignUpRequestDto
import com.terning.data.dto.response.SignInResponseDto
import com.terning.data.dto.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("api/v1/auth/sign-in")
    suspend fun postSignIn(
        @Header("Authorization") authorization: String,
        @Body body: SignInRequestDto,
    ): BaseResponse<SignInResponseDto>

    @POST("api/v1/auth/sign-up")
    suspend fun postSignUp(
        @Header("userId") authId: String,
        @Body body: SignUpRequestDto,
    ): BaseResponse<SignUpResponseDto>
}