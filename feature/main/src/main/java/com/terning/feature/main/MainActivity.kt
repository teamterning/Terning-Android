package com.terning.feature.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.CompositionLocalProvider
import com.terning.core.analytics.AmplitudeTracker
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.util.DeeplinkDefaults.INTERN_ID
import com.terning.core.designsystem.util.DeeplinkDefaults.REDIRECT
import com.terning.core.firebase.messageservice.TerningMessagingService.Companion.FROM_NOTIFICATION
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tracker: AmplitudeTracker

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            val (host, redirect, internId) = handleDeeplink(intent)

            TerningPointTheme {
                CompositionLocalProvider(LocalTracker provides tracker) {
                    MainScreen(
                        host = host,
                        redirect = redirect,
                        internId = internId,
                        navigator = navigator
                    )
                }
            }
        }
    }

    private fun handleDeeplink(intent: Intent?): Triple<String?, String?, String?> {
        val uri = intent?.data
        val uriString = uri?.toString()

        if (uriString.isNullOrEmpty()) return Triple(null, null, null)

        val host = uri.host
        val redirect = uri.getQueryParameter(REDIRECT)
        val internId = uri.getQueryParameter(INTERN_ID)

        if (!intent.getBooleanExtra(ALREADY_TRACKED, false)
            && intent.getBooleanExtra(FROM_NOTIFICATION, false)
        ) {
            tracker.track(
                type = EventType.PUSH_NOTIFICATION,
                name = "opened"
            )
        }

        intent.putExtra(ALREADY_TRACKED, true)

        return Triple(host, redirect, internId)
    }

    companion object {
        private const val ALREADY_TRACKED: String = "alreadyTracked"

        fun getIntent(
            context: Context,
        ) = Intent(context, MainActivity::class.java)
    }
}
