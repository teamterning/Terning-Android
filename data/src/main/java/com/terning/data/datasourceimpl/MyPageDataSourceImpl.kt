package com.terning.data.datasourceimpl

import com.terning.data.datasource.MyPageDataSource
import com.terning.data.dto.BaseResponse
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.dto.response.MyPageResponseDto
import com.terning.data.service.MyPageService
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageDataSource {
    override suspend fun postLogout(): NonDataBaseResponse = myPageService.postLogout()

    override suspend fun deleteQuit(): NonDataBaseResponse = myPageService.deleteQuit()

    override suspend fun getProfile(): BaseResponse<MyPageResponseDto> = myPageService.getProfile()
}