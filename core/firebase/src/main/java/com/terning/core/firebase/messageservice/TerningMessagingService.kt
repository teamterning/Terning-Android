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
import com.terning.core.analytics.AmplitudeTracker
import com.terning.core.analytics.EventType
import com.terning.core.designsystem.type.DeeplinkType
import com.terning.core.designsystem.util.DeeplinkDefaults
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

    @Inject
    lateinit var amplitudeTracker: AmplitudeTracker

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Timber.tag("okhttp").d("ON NEW TOKEN")
    }

    override fun handleIntent(intent: Intent?) {
        super.handleIntent(intent)

        if (!isAppInForeground()) {
            extractInformation(
                title = intent?.getStringExtra(TITLE),
                body = intent?.getStringExtra(BODY),
                type = intent?.getStringExtra(TYPE),
                imageUrl = intent?.getStringExtra(IMAGE_URL)
            )
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (isAppInForeground()) {
            extractInformation(
                title = message.data[TITLE],
                body = message.data[BODY],
                type = message.data[TYPE],
                imageUrl = message.data[IMAGE_URL]
            )
        }
    }

    private fun extractInformation(
        title: String?,
        body: String?,
        type: String?,
        imageUrl: String?
    ) {
        if (title.isNullOrEmpty() || !userRepository.getAlarmAvailable()) return

        amplitudeTracker.track(
            type = EventType.PUSH_NOTIFICATION,
            name = "received"
        )

        sendNotification(
            title = title,
            body = body.orEmpty(),
            type = type.orEmpty(),
            imageUrl = imageUrl.orEmpty()
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
        val deeplink = buildDeeplink(type, isForeground)
        val intent = navigatorProvider.getMainActivityIntent(deeplink = deeplink).apply {
            action = Intent.ACTION_VIEW
            data = deeplink.toUri()
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(FROM_NOTIFICATION, true)
        }
        val pendingIntent = PendingIntent.getActivity(
            this@TerningMessagingService,
            notifyId,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_MUTABLE
        )
        val notificationBuilder =
            NotificationCompat.Builder(this, CHANNEL_ID).apply {
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
                CHANNEL_ID,
                CHANNEL_ID,
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

        return appProcesses?.any {
            val isForeground =
                it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            val isCurrentApp = it.processName == packageName

            isForeground && isCurrentApp
        } == true
    }

    private fun buildDeeplink(type: String, isForeground: Boolean): String {
        val base = DeeplinkType.from(type) ?: return ""

        return if (isForeground) DeeplinkDefaults.build(host = base.path)
        else DeeplinkDefaults.build(host = BACKGROUND_HOST, redirect = base.path)
    }

    companion object {
        private const val CHANNEL_ID: String = "terning"
        private const val TITLE: String = "title"
        private const val BODY: String = "body"
        private const val TYPE: String = "type"
        private const val IMAGE_URL: String = "imageUrl"
        private const val BACKGROUND_HOST: String = "splash"
        const val FROM_NOTIFICATION: String = "fromNotification"
    }
}
