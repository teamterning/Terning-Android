package com.terning.domain.mypage.repository

import com.terning.domain.mypage.entity.AlarmStatus
import com.terning.domain.mypage.entity.MyPageProfile
import com.terning.domain.mypage.entity.MyPageProfileEdit

interface MyPageRepository {
    suspend fun postLogout(): Result<Unit>

    suspend fun deleteQuit(): Result<Unit>

    suspend fun getProfile(): Result<MyPageProfile>

    suspend fun editProfile(
        request: MyPageProfileEdit
    ): Result<Unit>

    suspend fun updateAlarmState(
        request: AlarmStatus
    ): Result<Unit>
}