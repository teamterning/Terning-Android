package com.terning.feature.main.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
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

        val name = intent.data?.getQueryParameter("redirect") ?: "undefined"
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            TerningPointTheme {
                CompositionLocalProvider(LocalTracker provides tracker) {
                    MainScreen(navigator)
                }
            }
        }
    }

    companion object {
        private const val EXTRA_TYPE = "EXTRA_DEFAULT"

        @JvmStatic
        fun getIntent(
            context: Context,
            type: String? = null,
        ) = Intent(context, MainActivity::class.java).apply {
            putExtra(EXTRA_TYPE, type)
        }
    }
}