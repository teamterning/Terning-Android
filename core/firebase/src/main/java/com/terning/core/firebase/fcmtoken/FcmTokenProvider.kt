package com.terning.core.firebase.fcmtoken

interface FcmTokenProvider {
    suspend fun fetchFcmToken(): String
}