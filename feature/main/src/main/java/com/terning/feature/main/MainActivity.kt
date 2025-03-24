package com.terning.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.terning.core.analytics.AmplitudeTracker
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.extension.toast
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
            val intentData = intent.getStringExtra(EXTRA_TYPE)
            val isFromNotification = intentData != null
            val context = LocalContext.current
            LaunchedEffect(true) {
                if (isFromNotification) {
                    Toast.makeText(context, "$intentData", Toast.LENGTH_SHORT).show()
                }
            }

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