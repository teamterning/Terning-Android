package com.terning.domain.mypage.repository

import com.terning.domain.entity.mypage.MyPageProfile
import com.terning.domain.entity.mypage.MyPageProfileEdit

interface MyPageRepository {
    suspend fun postLogout(): Result<Unit>

    suspend fun deleteQuit(): Result<Unit>

    suspend fun getProfile(): Result<MyPageProfile>

    suspend fun editProfile(
        request: MyPageProfileEdit
    ): Result<Unit>
}