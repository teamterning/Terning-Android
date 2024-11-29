package com.terning.feature.calendar.month

import androidx.annotation.StringRes

sealed class CalendarMonthSideEffect {
    data class ShowToast(@StringRes val message: Int) : CalendarMonthSideEffect()
}