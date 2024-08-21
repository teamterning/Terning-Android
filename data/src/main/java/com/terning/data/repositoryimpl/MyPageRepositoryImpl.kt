package com.terning.data.repositoryimpl

import com.terning.data.datasource.MyPageDataSource
import com.terning.data.mapper.mypage.toMyPageProfile
import com.terning.domain.entity.mypage.MyPageProfileModel
import com.terning.domain.repository.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val myPageDataSource: MyPageDataSource
) : MyPageRepository {
    override suspend fun postLogout(): Result<Unit> =
        runCatching {
            myPageDataSource.postLogout()
        }

    override suspend fun deleteQuit(): Result<Unit> =
        runCatching {
            myPageDataSource.deleteQuit()
        }

    override suspend fun getProfile(): Result<MyPageProfileModel> =
        runCatching {
            myPageDataSource.getProfile().result.toMyPageProfile()
        }
}