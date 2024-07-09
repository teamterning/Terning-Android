package com.terning.feature.calendar.models

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal object CalendarDefaults {
    /**
     * Returns the fling behavior for the given [LazyListState].
     * From Github [https://github.com/kizitonwose/Calendar]
     */
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun flingBehavior(state: LazyListState): FlingBehavior {
        val snappingLayout = remember(state) {
            val provider = SnapLayoutInfoProvider(state, SnapPosition.Start)
            CalendarSnapLayoutInfoProvider(provider)
        }
        return rememberSnapFlingBehavior(snappingLayout)
    }
}

@ExperimentalFoundationApi
@Suppress("FunctionName")
private fun CalendarSnapLayoutInfoProvider(
    snapLayoutInfoProvider: SnapLayoutInfoProvider,
): SnapLayoutInfoProvider = object : SnapLayoutInfoProvider by snapLayoutInfoProvider {
    override fun calculateApproachOffset(velocity: Float, decayOffset: Float): Float = 0f
}