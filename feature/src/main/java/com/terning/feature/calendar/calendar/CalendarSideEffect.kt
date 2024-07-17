package com.terning.feature.calendar.calendar

import androidx.annotation.StringRes

sealed class CalendarSideEffect {
    class ShowToast(@StringRes val message: Int) : CalendarSideEffect()
}