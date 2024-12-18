package com.terning.feature.calendar.calendar.type

enum class WeekDay(
    val nameInKorean: String,
) {
    SUNDAY(nameInKorean = "일"),
    MONDAY(nameInKorean = "월"),
    TUESDAY(nameInKorean = "화"),
    WEDNESDAY(nameInKorean = "수"),
    THURSDAY(nameInKorean = "목"),
    FRIDAY(nameInKorean = "금"),
    SATURDAY(nameInKorean = "토");

    companion object {
        fun isSunday(weekDay: WeekDay): Boolean {
            return weekDay == SUNDAY
        }
    }
}
