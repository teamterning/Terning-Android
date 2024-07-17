package com.terning.data.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.response.MyPageResponseDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface MyPageService {
    @POST("api/v1/auth/logout")
    suspend fun postLogout(): NonDataBaseResponse

    @DELETE("api/v1/auth/withdraw")
    suspend fun deleteQuit(): NonDataBaseResponse

    @GET("api/v1/mypage/profile")
    suspend fun getProfile(): BaseResponse<MyPageResponseDto>
}