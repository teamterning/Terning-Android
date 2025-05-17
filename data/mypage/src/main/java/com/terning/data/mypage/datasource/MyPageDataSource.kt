package com.terning.data.mypage.datasource

import com.terning.core.network.BaseResponse
import com.terning.core.network.NonDataBaseResponse
import com.terning.data.mypage.dto.request.AlarmStatusRequestDto
import com.terning.data.mypage.dto.request.MyPageProfileEditRequestDto
import com.terning.data.mypage.dto.response.MyPageResponseDto

interface MyPageDataSource {
    suspend fun postLogout(): NonDataBaseResponse

    suspend fun deleteQuit(): NonDataBaseResponse

    suspend fun getProfile(): BaseResponse<MyPageResponseDto>

    suspend fun editProfile(
        request: MyPageProfileEditRequestDto
    ): NonDataBaseResponse

    suspend fun updateAlarmState(
        request : AlarmStatusRequestDto
    ) : NonDataBaseResponse
}
