package com.terning.data.mypage.repositoryimpl

import com.terning.data.mypage.datasource.MyPageDataSource
import com.terning.data.mypage.mapper.toMyPageProfile
import com.terning.data.mypage.mapper.toMyPageProfileEditRequestDto
import com.terning.domain.mypage.entity.MyPageProfile
import com.terning.domain.mypage.entity.MyPageProfileEdit
import com.terning.domain.mypage.repository.MyPageRepository
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

    override suspend fun getProfile(): Result<MyPageProfile> =
        runCatching {
            myPageDataSource.getProfile().result.toMyPageProfile()
        }

    override suspend fun editProfile(
        request: MyPageProfileEdit
    ): Result<Unit> =
        runCatching {
            myPageDataSource.editProfile(
                request.toMyPageProfileEditRequestDto()
            )
        }
}