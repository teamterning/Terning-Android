package com.terning.core.designsystem.util

import com.terning.core.designsystem.extension.currentMonth
import com.terning.core.designsystem.extension.currentYear
import java.util.Calendar

object CalendarDefaults {
    const val START_YEAR = 2010
    val END_YEAR =
        if (Calendar.getInstance().currentMonth >= 10) Calendar.getInstance().currentYear + 1
        else Calendar.getInstance().currentYear
    const val START_MONTH = 1
    const val END_MONTH = 12
}
