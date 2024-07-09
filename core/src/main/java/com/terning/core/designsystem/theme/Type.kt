package com.terning.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.terning.core.R

val PretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular))
val PretendardLight = FontFamily(Font(R.font.pretendard_light))

@Stable
class TerningTypography internal constructor(
    heading1: TextStyle,
    heading2: TextStyle,
    title1: TextStyle,
    title2: TextStyle,
    title3: TextStyle,
    title4: TextStyle,
    title5: TextStyle,
    body0: TextStyle,
    body1: TextStyle,
    body2: TextStyle,
    body3: TextStyle,
    body4: TextStyle,
    body5: TextStyle,
    body6: TextStyle,
    body7: TextStyle,
    button0: TextStyle,
    button1: TextStyle,
    button2: TextStyle,
    button3: TextStyle,
    button4: TextStyle,
    button5: TextStyle,
    button6: TextStyle,
    detail0: TextStyle,
    detail1: TextStyle,
    detail2: TextStyle,
    detail3: TextStyle,
    detail4: TextStyle,
    detail5: TextStyle,
    calendar: TextStyle,
) {
    var heading1: TextStyle by mutableStateOf(heading1)
        private set
    var heading2: TextStyle by mutableStateOf(heading2)
        private set
    var title1: TextStyle by mutableStateOf(title1)
        private set
    var title2: TextStyle by mutableStateOf(title2)
        private set
    var title3: TextStyle by mutableStateOf(title3)
        private set
    var title4: TextStyle by mutableStateOf(title4)
        private set
    var title5: TextStyle by mutableStateOf(title5)
        private set
    var body0: TextStyle by mutableStateOf(body0)
        private set
    var body1: TextStyle by mutableStateOf(body1)
        private set
    var body2: TextStyle by mutableStateOf(body2)
        private set
    var body3: TextStyle by mutableStateOf(body3)
        private set
    var body4: TextStyle by mutableStateOf(body4)
        private set
    var body5: TextStyle by mutableStateOf(body5)
        private set
    var body6: TextStyle by mutableStateOf(body6)
        private set
    var body7: TextStyle by mutableStateOf(body7)
        private set
    var button0: TextStyle by mutableStateOf(button0)
        private set
    var button1: TextStyle by mutableStateOf(button1)
        private set
    var button2: TextStyle by mutableStateOf(button2)
        private set
    var button3: TextStyle by mutableStateOf(button3)
        private set
    var button4: TextStyle by mutableStateOf(button4)
        private set
    var button5: TextStyle by mutableStateOf(button5)
        private set
    var button6: TextStyle by mutableStateOf(button6)
        private set
    var detail0: TextStyle by mutableStateOf(detail0)
        private set
    var detail1: TextStyle by mutableStateOf(detail1)
        private set
    var detail2: TextStyle by mutableStateOf(detail2)
        private set
    var detail3: TextStyle by mutableStateOf(detail3)
        private set
    var detail4: TextStyle by mutableStateOf(detail4)
        private set
    var detail5: TextStyle by mutableStateOf(detail5)
        private set
    var calendar: TextStyle by mutableStateOf(calendar)
        private set

    fun copy(
        heading1: TextStyle = this.heading1,
        heading2: TextStyle = this.heading2,
        title1: TextStyle = this.title1,
        title2: TextStyle = this.title2,
        title3: TextStyle = this.title3,
        title4: TextStyle = this.title4,
        title5: TextStyle = this.title5,
        body0: TextStyle = this.body0,
        body1: TextStyle = this.body1,
        body2: TextStyle = this.body2,
        body3: TextStyle = this.body3,
        body4: TextStyle = this.body4,
        body5: TextStyle = this.body5,
        body6: TextStyle = this.body6,
        body7: TextStyle = this.body7,
        button0: TextStyle = this.button0,
        button1: TextStyle = this.button1,
        button2: TextStyle = this.button2,
        button3: TextStyle = this.button3,
        button4: TextStyle = this.button4,
        button5: TextStyle = this.button5,
        button6: TextStyle = this.button6,
        detail0: TextStyle = this.detail0,
        detail1: TextStyle = this.detail1,
        detail2: TextStyle = this.detail2,
        detail3: TextStyle = this.detail3,
        detail4: TextStyle = this.detail4,
        detail5: TextStyle = this.detail5,
        calendar: TextStyle = this.calendar,
    ): TerningTypography = TerningTypography(
        heading1,
        heading2,
        title1,
        title2,
        title3,
        title4,
        title5,
        body0,
        body1,
        body2,
        body3,
        body4,
        body5,
        body6,
        body7,
        button0,
        button1,
        button2,
        button3,
        button4,
        button5,
        button6,
        detail0,
        detail1,
        detail2,
        detail3,
        detail4,
        detail5,
        calendar,
    )

    fun update(other: TerningTypography) {
        heading1 = other.heading1
        heading2 = other.heading2
        title1 = other.title1
        title2 = other.title2
        title3 = other.title3
        title4 = other.title4
        title5 = other.title5
        body0 = other.body0
        body1 = other.body1
        body2 = other.body2
        body3 = other.body3
        body4 = other.body4
        body5 = other.body5
        body6 = other.body6
        body7 = other.body7
        button0 = other.button0
        button1 = other.button1
        button2 = other.button2
        button3 = other.button3
        button4 = other.button4
        button5 = other.button5
        button6 = other.button6
        detail0 = other.detail0
        detail1 = other.detail1
        detail2 = other.detail2
        detail3 = other.detail3
        detail4 = other.detail4
        detail5 = other.detail5
        calendar = other.calendar
    }
}

@Composable
fun TerningTypography(): TerningTypography {
    return TerningTypography(
        heading1 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 23.sp,
            letterSpacing = (-0.005).em,
            lineHeight = 23.sp,
        ),
        heading2 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 21.sp,
            letterSpacing = (-0.005).em,
            lineHeight = (27.3).sp,
        ),
        title1 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 19.sp,
            letterSpacing = (-0.005).em,
            lineHeight = (22.8).sp,
        ),
        title2 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            letterSpacing = (-0.005).em,
            lineHeight = (22.1).sp,
        ),
        title3 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            letterSpacing = (-0.005).em,
            lineHeight = (22.1).sp,
        ),
        title4 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            letterSpacing = (-0.005).em,
            lineHeight = (19.5).sp,
        ),
        title5 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = (0.002).em,
            lineHeight = (16.8).sp,
        ),
        body0 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 19.sp,
            letterSpacing = (0.002).em,
            lineHeight = 19.sp,
        ),
        body1 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            letterSpacing = (0.002).em,
            lineHeight = 15.sp,
        ),
        body2 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = (0.002).em,
            lineHeight = (16.8).sp,
        ),
        body3 = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            letterSpacing = (0.002).em,
            lineHeight = (15.6).sp,
        ),
        body4 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            letterSpacing = (0.002).em,
            lineHeight = (19.5).sp,
        ),
        body5 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            letterSpacing = (0.002).em,
            lineHeight = (15.6).sp,
        ),
        body6 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            letterSpacing = (0.002).em,
            lineHeight = (13.2).sp,
        ),
        body7 = TextStyle(
            fontFamily = PretendardLight,
            fontWeight = FontWeight.Light,
            fontSize = 11.sp,
            letterSpacing = (0.002).em,
            lineHeight = 11.sp,
        ),
        button0 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            letterSpacing = (0.002).em,
            lineHeight = 15.sp,
        ),
        button1 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            letterSpacing = (0.002).em,
            lineHeight = 15.sp,
        ),
        button2 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            letterSpacing = (0.002).em,
            lineHeight = 18.sp,
        ),
        button3 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            letterSpacing = (0.002).em,
            lineHeight = (15.6).sp,
        ),
        button4 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            letterSpacing = (0).em,
            lineHeight = 11.sp,
        ),
        button5 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            letterSpacing = (0.002).em,
            lineHeight = 15.sp,
        ),
        button6 = TextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 9.sp,
            letterSpacing = (0.002).em,
            lineHeight = 9.sp,
        ),
        detail0 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            letterSpacing = (0.002).em,
            lineHeight = 13.sp,
        ),
        detail1 = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            letterSpacing = (0.002).em,
            lineHeight = (15.6).sp,
        ),
        detail2 = TextStyle(
            fontFamily = PretendardLight,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            letterSpacing = (0.002).em,
            lineHeight = (14.4).sp,
        ),
        detail3 = TextStyle(
            fontFamily = PretendardLight,
            fontWeight = FontWeight.Light,
            fontSize = 11.sp,
            letterSpacing = (0.002).em,
            lineHeight = (13.2).sp,
        ),
        detail4 = TextStyle(
            fontFamily = PretendardLight,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp,
            letterSpacing = (0.002).em,
            lineHeight = 10.sp,
        ),
        detail5 = TextStyle(
            fontFamily = PretendardLight,
            fontWeight = FontWeight.Light,
            fontSize = 9.sp,
            letterSpacing = (0.002).em,
            lineHeight = 9.sp,
        ),
        calendar = TextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            letterSpacing = (0.002).em,
            lineHeight = 15.sp,
        ),
    )
}
