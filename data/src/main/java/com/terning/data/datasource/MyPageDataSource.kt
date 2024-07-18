package com.terning.data.datasource

import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.response.MyPageResponseDto

interface MyPageDataSource {
    suspend fun postLogout(): NonDataBaseResponse

    suspend fun deleteQuit(): NonDataBaseResponse

    suspend fun getProfile(): BaseResponse<MyPageResponseDto>
}