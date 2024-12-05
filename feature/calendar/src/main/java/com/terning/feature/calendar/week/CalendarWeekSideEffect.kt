package com.terning.feature.calendar.week

import androidx.annotation.StringRes

sealed class CalendarWeekSideEffect {
    data class ShowToast(@StringRes val message: Int) : CalendarWeekSideEffect()
}