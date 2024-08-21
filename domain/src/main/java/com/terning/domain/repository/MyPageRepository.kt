package com.terning.domain.repository

import com.terning.domain.entity.mypage.MyPageProfileModel

interface MyPageRepository {
    suspend fun postLogout(): Result<Unit>

    suspend fun deleteQuit(): Result<Unit>

    suspend fun getProfile(): Result<MyPageProfileModel>
}