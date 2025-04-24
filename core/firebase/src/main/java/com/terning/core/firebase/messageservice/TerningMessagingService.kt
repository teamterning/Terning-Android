package com.terning.core.firebase.messageservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.terning.core.firebase.R
import com.terning.domain.user.repository.UserRepository
import com.terning.navigator.NavigatorProvider
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class TerningMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var navigatorProvider: NavigatorProvider

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Timber.tag("okhttp").d("ON NEW TOKEN")
    }

    override fun handleIntent(intent: Intent?) {
        super.handleIntent(intent)

        if (intent?.getStringExtra(TITLE)?.isEmpty() == true
            || !userRepository.getAlarmAvailable()
        ) return

        val title = intent?.getStringExtra(TITLE).orEmpty()
        val body = intent?.getStringExtra(BODY).orEmpty()
        val type = intent?.getStringExtra(TYPE).orEmpty()

        sendNotification(
            title = title,
            body = body,
            type = type
        )
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.data.isEmpty()
            || !userRepository.getAlarmAvailable()
        ) return

        val title = message.data[TITLE].orEmpty()
        val body = message.data[BODY].orEmpty()
        val type = message.data[TYPE].orEmpty()

        sendNotification(
            title = title,
            body = body,
            type = type
        )
    }

    private fun sendNotification(title: String, body: String, type: String) {
        val notifyId = Random().nextInt()
        val intent = navigatorProvider.getMainActivityIntent(deeplink = type).apply {
            action = Intent.ACTION_VIEW
            data = type.toUri()
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this@TerningMessagingService,
            notifyId,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_MUTABLE
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
            createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelId,
                    NotificationManager.IMPORTANCE_HIGH,
                ),
            )
            notify(notifyId, notificationBuilder.build())
        }
    }

    companion object {
        private const val CHANNEL_ID: String = "terning"
        private const val TITLE: String = "title"
        private const val BODY: String = "body"
        private const val TYPE: String = "type"
    }
}