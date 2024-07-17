package com.terning.data.repositoryimpl

import com.terning.data.datasource.MyPageDataSource
import com.terning.domain.repository.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val myPageDataSource: MyPageDataSource
) : MyPageRepository {
    override suspend fun postLogout(): Result<Unit> =
        runCatching {
            myPageDataSource.postLogout()
        }
}