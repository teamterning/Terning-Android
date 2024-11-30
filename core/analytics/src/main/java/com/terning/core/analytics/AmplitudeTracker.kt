package com.terning.core.analytics

import android.content.Context
import androidx.compose.runtime.staticCompositionLocalOf
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import com.amplitude.common.android.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

val LocalTracker = staticCompositionLocalOf<AmplitudeTracker> {
    error("No AmplitudeTracker provided")
}

class AmplitudeTracker @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val amplitude = Amplitude(
        Configuration(
            apiKey = BuildConfig.AMPLITUDE_KEY,
            context = context
        )
    )

    fun track(type: EventType, name: String, properties: Map<String, Any?> = emptyMap()) {
        if (BuildConfig.DEBUG) {
            Timber.d("Amplitude: ${type.prefix}_$name properties: $properties")
        }
        amplitude.track(eventType = "${type.prefix}_$name", eventProperties = properties)
    }
}
