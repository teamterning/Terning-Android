package com.terning.core.extension

import java.util.Calendar

val Calendar.currentYear: Int get() = get(Calendar.YEAR)

val Calendar.currentMonth: Int get() = get(Calendar.MONTH) + 1