package com.terning.data.mypage.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.request.MyPageProfileEditRequestDto
import com.terning.data.dto.response.MyPageResponseDto

interface MyPageDataSource {
    suspend fun postLogout(): NonDataBaseResponse

    suspend fun deleteQuit(): NonDataBaseResponse

    suspend fun getProfile(): BaseResponse<MyPageResponseDto>

    suspend fun editProfile(
        request: MyPageProfileEditRequestDto
    ): NonDataBaseResponse
}