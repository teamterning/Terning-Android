package com.terning.core.firebase.fcmtoken

import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FcmTokenProviderImpl : FcmTokenProvider {
    override suspend fun fetchFcmToken(): String = suspendCoroutine { continuation ->
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Timber.tag("okhttp").d("FCM TOKEN : ${task.result}")
                continuation.resume(task.result)
            } else {
                continuation.resumeWithException(task.exception ?: Exception())
            }
        }
    }
}