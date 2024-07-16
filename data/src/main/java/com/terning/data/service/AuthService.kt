package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.request.SignInRequestDto
import com.terning.data.dto.response.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/auth/sign-in")
    suspend fun postSignIn(
        @Header("Authorization") authorization: String,
        @Body body: SignInRequestDto,
    ): BaseResponse<SignInResponseDto>
}