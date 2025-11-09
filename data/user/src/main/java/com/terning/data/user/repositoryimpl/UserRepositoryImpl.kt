package com.terning.data.user.repositoryimpl

import com.terning.core.firebase.fcmtoken.FcmTokenProvider
import com.terning.core.local.TerningDataStore
import com.terning.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val terningDataStore: TerningDataStore,
    private val fcmTokenProvider: FcmTokenProvider
) : UserRepository {
    override fun getAccessToken(): String = terningDataStore.accessToken

    override fun setAccessToken(accessToken: String) {
        terningDataStore.accessToken = accessToken
    }

    override fun getRefreshToken(): String = terningDataStore.refreshToken

    override fun setRefreshToken(refreshToken: String) {
        terningDataStore.refreshToken = refreshToken
    }

    override fun getFcmToken(): String = terningDataStore.fcmToken

    override suspend fun fetchAndSetFcmToken(): Result<Unit> = runCatching {
        terningDataStore.fcmToken = fcmTokenProvider.fetchFcmToken()
    }

    override fun getUserId(): Long = terningDataStore.userId

    override fun setUserId(userId: Long) {
        terningDataStore.userId = userId
    }

    override fun getAlarmAvailable(): Boolean = terningDataStore.alarmAvailable

    override fun setAlarmAvailable(availability: Boolean) {
        terningDataStore.alarmAvailable = availability
    }

    override fun setPermissionRequested(requested: Boolean) {
        terningDataStore.hasRequestedPermission = requested
    }

    override fun getPermissionRequested(): Boolean = terningDataStore.hasRequestedPermission

    override fun clearInfo() {
        terningDataStore.clearInfo()
    }

    override fun hasNoticeCooldownPassed(): Boolean {
        val lastShownTimestamp = terningDataStore.serverNoticeTimestamp

        // 2. 0L이면 (앱 설치 후 본 적 없음) -> 무조건 true 반환 (공지 보여줘야 함)
        if (lastShownTimestamp == 0L) {
            return true
        }

        // 3. 현재 시간 가져오기
        val currentTime = System.currentTimeMillis()

        // 4. (현재 시간 - 저장된 시간) = 경과 시간
        val elapsedTime = currentTime - lastShownTimestamp

        // 5. 경과 시간이 3시간(밀리초)보다 크면 true
        return elapsedTime > THREE_HOURS_MS
    }

    override fun setNoticeTimestampToNow() {
        terningDataStore.serverNoticeTimestamp = System.currentTimeMillis()
    }

    companion object {
        // 3시간을 밀리초로 환산한 값
        private const val THREE_HOURS_MS = 3 * 60 * 60 * 1000L
    }
}
