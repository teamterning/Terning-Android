package com.terning.point

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class TerningMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        // FCM 토큰이 갱신될 때마다 호출
        super.onNewToken(token)
        Log.d("MyFirebaseMessagingService", "fcm token : $token")
    }
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("MyFirebaseMessagingService", message.notification.toString())
        Log.d("MyFirebaseMessagingService", message.data.toString())
        Log.d("MyFirebaseMessagingService", "터닝 화이팅")
    }
}