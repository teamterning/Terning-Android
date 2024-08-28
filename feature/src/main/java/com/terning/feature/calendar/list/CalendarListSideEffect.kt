package com.terning.feature.calendar.list

import androidx.annotation.StringRes

sealed class CalendarListSideEffect {
    data class ShowToast(@StringRes val message: Int) : CalendarListSideEffect()
}