package com.terning.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.terning.core.analytics.AmplitudeTracker
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.theme.TerningPointTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tracker: AmplitudeTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            Log.d("LYB", "intent.data = ${intent.data.toString()}")
            val redirect: String? = intent.data?.getQueryParameter(REDIRECT)

            TerningPointTheme {
                CompositionLocalProvider(LocalTracker provides tracker) {
                    MainScreen(navigator, redirect)
                }
            }
        }
    }

    companion object {
        private const val REDIRECT: String = "redirect"

        @JvmStatic
        fun getIntent(
            context: Context,
        ) = Intent(context, MainActivity::class.java)
    }
}