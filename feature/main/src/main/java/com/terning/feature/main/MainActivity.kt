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
import com.terning.core.designsystem.util.DeeplinkDefaults.REDIRECT
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
            val (host, redirect) = handleDeeplink(intent)

            TerningPointTheme {
                CompositionLocalProvider(LocalTracker provides tracker) {
                    MainScreen(
                        host = host,
                        redirect = redirect,
                        navigator = navigator
                    )
                }
            }
        }
    }

    private fun handleDeeplink(intent: Intent?): Pair<String?, String?> {
        val uri = intent?.data
        val host = uri?.host
        val redirect = uri?.getQueryParameter(REDIRECT)

        if (uri != null) {
            tracker.track(
                type = EventType.PUSH_NOTIFICATION,
                name = "opened"
            )
        }

        return host to redirect
    }

    companion object {
        fun getIntent(
            context: Context,
        ) = Intent(context, MainActivity::class.java)
    }
}