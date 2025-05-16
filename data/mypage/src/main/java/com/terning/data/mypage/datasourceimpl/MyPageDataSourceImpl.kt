package com.terning.data.mypage.datasourceimpl

import com.terning.core.network.BaseResponse
import com.terning.core.network.NonDataBaseResponse
import com.terning.data.mypage.datasource.MyPageDataSource
import com.terning.data.mypage.dto.request.AlarmStatusRequestDto
import com.terning.data.mypage.dto.request.MyPageProfileEditRequestDto
import com.terning.data.mypage.dto.response.MyPageResponseDto
import com.terning.data.mypage.service.MyPageService
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageDataSource {
    override suspend fun postLogout(): NonDataBaseResponse = myPageService.postLogout()

    override suspend fun deleteQuit(): NonDataBaseResponse = myPageService.deleteQuit()

    override suspend fun getProfile(): BaseResponse<MyPageResponseDto> = myPageService.getProfile()

    override suspend fun editProfile(
        request: MyPageProfileEditRequestDto
    ): NonDataBaseResponse = myPageService.editProfile(request)

    override suspend fun updateAlarmState(request: AlarmStatusRequestDto): NonDataBaseResponse =
        myPageService.patchAlarmStatus(request)
}