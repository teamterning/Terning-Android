package com.terning.data.mypage.service

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface MyPageService {
    @POST("api/v1/auth/logout")
    suspend fun postLogout(): NonDataBaseResponse

    @DELETE("api/v1/auth/withdraw")
    suspend fun deleteQuit(): NonDataBaseResponse

    @GET("api/v1/mypage/profile")
    suspend fun getProfile(): BaseResponse<com.terning.data.mypage.dto.response.MyPageResponseDto>

    @PATCH("api/v1/mypage/profile")
    suspend fun editProfile(
        @Body body: com.terning.data.mypage.dto.request.MyPageProfileEditRequestDto
    ): NonDataBaseResponse
}