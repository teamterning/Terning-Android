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
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.theme.TerningPointTheme
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
            val redirect: String? = intent.data?.getQueryParameter(REDIRECT)

            TerningPointTheme {
                CompositionLocalProvider(LocalTracker provides tracker) {
                    MainScreen(
                        redirect = redirect,
                        navigator = navigator
                    )
                }
            }
        }
    }

    companion object {
        private const val REDIRECT: String = "redirect"

        fun getIntent(
            context: Context,
        ) = Intent(context, MainActivity::class.java)
    }
}