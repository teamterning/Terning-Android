package com.terning.data.datasourceimpl

import com.terning.data.datasource.MyPageDataSource
import com.terning.data.dto.NonDataBaseResponse
import com.terning.data.service.MyPageService
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageDataSource {
    override suspend fun patchLogout(): NonDataBaseResponse = myPageService.patchLogout()
}