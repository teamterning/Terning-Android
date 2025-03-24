package com.terning.point

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.terning.core.local.TerningDataStore
import com.terning.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TerningMessagingService : FirebaseMessagingService() {  // todo : 파이어베이스 모듈로 옮기기 by leeeyubin

    @Inject
    lateinit var terningDataStore: TerningDataStore

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Timber.tag("okhttp").d("ON NEW TOKEN")
    }

    override fun handleIntent(intent: Intent?) {
        super.handleIntent(intent)
        if (intent?.getStringExtra("title")?.isNullOrEmpty() == true) return

        val title = intent?.getStringExtra("title").orEmpty()
        val body = intent?.getStringExtra("body").orEmpty()
        val type = intent?.getStringExtra("type").orEmpty()
        sendNotification(title, body, type)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.data.isEmpty() || !terningDataStore.alarmAvailable) return

        val title = message.data["title"].orEmpty()
        val body = message.data["body"].orEmpty()
        val type = message.data["type"].orEmpty()

        sendNotification(title = title, body = body, type)
    }

    private fun sendNotification(title: String?, body: String?, type: String?) {
        val notifyId = System.currentTimeMillis().toInt()
        val intent =
            MainActivity.getIntent(this, type = type).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            notifyId,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_MUTABLE,
        )
        val channelId: String = CHANNEL_ID
        val notificationBuilder =
            NotificationCompat.Builder(this, channelId).apply {
                setSmallIcon(R.mipmap.ic_terning_launcher)
                setContentTitle(title)
                setContentText(body)
                setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
                setContentIntent(pendingIntent)
            }

        getSystemService<NotificationManager>()?.run {
            notify(notifyId, notificationBuilder.build())
        }
    }

    companion object {
        private const val CHANNEL_ID = "TERNING"
        private const val TYPE = "TYPE"
    }
}