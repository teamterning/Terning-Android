package com.terning.core.firebase.messageservice

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import coil3.BitmapImage
import coil3.ImageLoader
import coil3.request.ImageRequest
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
        val imageUrl = intent?.getStringExtra(IMAGE_URL).orEmpty()

        sendNotification(
            title = title,
            body = body,
            type = type,
            imageUrl = imageUrl
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
        val imageUrl = message.data[IMAGE_URL].orEmpty()

        sendNotification(
            title = title,
            body = body,
            type = type,
            imageUrl = imageUrl
        )
    }

    private fun sendNotification(
        title: String,
        body: String,
        type: String,
        imageUrl: String
    ) {
        val notifyId = Random().nextInt()
        val isForeground = isAppInForeground()
        val redirect: String = when (type) {
            "CALENDAR" -> if (isForeground) {
                "terning://calendar"
            } else {
                "terning://splash?redirect=calendar"
            }

            "HOME" -> if (isForeground) {
                "terning://home"
            } else {
                "terning://splash?redirect=home"
            }

            "SEARCH" -> if (isForeground) {
                "terning://search"
            } else {
                "terning://splash?redirect=search"
            }

            else -> ""
        }
        val intent = navigatorProvider.getMainActivityIntent(deeplink = redirect).apply {
            action = Intent.ACTION_VIEW
            data = redirect.toUri()
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
                setAutoCancel(true)
                setPriority(NotificationCompat.PRIORITY_HIGH)
                setContentIntent(pendingIntent)
            }
        val notificationManager = getSystemService<NotificationManager>()
        notificationManager?.createNotificationChannel(
            NotificationChannel(
                channelId,
                channelId,
                NotificationManager.IMPORTANCE_HIGH
            )
        )
        val imageLoader = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data(imageUrl)
            .target(
                onSuccess = { image ->
                    val bitmap = (image as BitmapImage).bitmap
                    notificationBuilder.setLargeIcon(bitmap)
                    notificationManager?.notify(notifyId, notificationBuilder.build())
                },
                onError = {
                    notificationManager?.notify(notifyId, notificationBuilder.build())
                }
            )
            .build()
        imageLoader.enqueue(request)
    }

    private fun isAppInForeground(): Boolean {
        val appProcesses =
            (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).runningAppProcesses
        val packageName = packageName
        return appProcesses?.any {
            it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                    it.processName == packageName
        } == true
    }

    companion object {
        private const val CHANNEL_ID: String = "terning"
        private const val TITLE: String = "title"
        private const val BODY: String = "body"
        private const val TYPE: String = "type"
        private const val IMAGE_URL: String = "imageUrl"
    }
}

