package com.terning.data.mypage.service

import com.terning.core.network.BaseResponse
import com.terning.core.network.NonDataBaseResponse
import com.terning.data.mypage.dto.request.AlarmStatusRequestDto
import com.terning.data.mypage.dto.request.MyPageProfileEditRequestDto
import com.terning.data.mypage.dto.response.MyPageResponseDto
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
    suspend fun getProfile(): BaseResponse<MyPageResponseDto>

    @PATCH("api/v1/mypage/profile")
    suspend fun editProfile(
        @Body body: MyPageProfileEditRequestDto
    ): NonDataBaseResponse

    @PATCH("api/v1/push-status")
    suspend fun patchAlarmStatus(
        @Body body: AlarmStatusRequestDto
    ): NonDataBaseResponse
}