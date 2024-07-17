package com.terning.data.service

import com.terning.data.dto.NonDataBaseResponse
import retrofit2.http.PATCH

interface MyPageService {
    @PATCH("api/v1/mypage/logout")
    suspend fun patchLogout(): NonDataBaseResponse

}