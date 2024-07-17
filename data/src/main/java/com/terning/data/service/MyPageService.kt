package com.terning.data.service

import com.terning.data.dto.NonDataBaseResponse
import retrofit2.http.POST

interface MyPageService {
    @POST("api/v1/auth/logout")
    suspend fun postLogout(): NonDataBaseResponse
}