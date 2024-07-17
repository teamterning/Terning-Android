package com.terning.data.datasource

import com.terning.data.dto.NonDataBaseResponse

interface MyPageDataSource {
    suspend fun patchLogout(): NonDataBaseResponse
}