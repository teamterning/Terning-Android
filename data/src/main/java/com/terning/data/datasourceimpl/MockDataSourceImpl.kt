//package com.terning.data.datasourceimpl
//
//import com.terning.data.datasource.MockDataSource
//import com.terning.data.dto.response.MockResponseDto
//import com.terning.data.service.MockService
//import javax.inject.Inject
//
//class MockDataSourceImpl @Inject constructor(
//    private val mockService: MockService
//) : MockDataSource {
//    override suspend fun getMock(page: Int): MockResponseDto =
//        mockService.getMockListFromServer(page)
//}