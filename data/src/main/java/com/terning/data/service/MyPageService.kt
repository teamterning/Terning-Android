package com.terning.data.service

import com.terning.data.dto.NonDataBaseResponse
import retrofit2.http.DELETE
import retrofit2.http.POST

interface MyPageService {
    @POST("api/v1/auth/logout")
    suspend fun postLogout(): NonDataBaseResponse

    @DELETE("api/v1/auth/withdraw")
    suspend fun deleteQuit(): NonDataBaseResponse
}