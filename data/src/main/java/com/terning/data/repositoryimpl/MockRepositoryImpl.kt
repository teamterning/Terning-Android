package com.terning.data.repositoryimpl

import com.terning.data.datasource.MockDataSource
import com.terning.domain.entity.response.MockResponseModel
import com.terning.domain.repository.MockRepository
import javax.inject.Inject

class MockRepositoryImpl @Inject constructor(private val mockDataSource: MockDataSource) :
    MockRepository {
    override suspend fun getMockList(page: Int): Result<List<MockResponseModel>> =
        runCatching {
            mockDataSource.getMock(page).toMockEntity()
        }
}