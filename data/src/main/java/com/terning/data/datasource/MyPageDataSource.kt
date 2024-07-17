package com.terning.data.datasource

import com.terning.data.dto.NonDataBaseResponse

interface MyPageDataSource {
    suspend fun postLogout(): NonDataBaseResponse

    suspend fun deleteQuit(): NonDataBaseResponse
}