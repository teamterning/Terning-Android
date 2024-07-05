package com.terning.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.terning.core.R

val PretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular))
val PretendardLight = FontFamily(Font(R.font.pretendard_light))

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

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
}


@Composable
fun TerningTypography(): TerningTypography {
    return TerningTypography(
        heading1 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 23.sp,
            letterSpacing = (-0.5).sp,
            lineHeight = 100.sp,
        ),
        heading2 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 21.sp,
            letterSpacing = (-0.5).sp,
            lineHeight = 130.sp,
        ),
        title1 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 19.sp,
            letterSpacing = (-0.5).sp,
            lineHeight = 120.sp,
        ),
        title2 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 17.sp,
            letterSpacing = (-0.5).sp,
            lineHeight = 130.sp,
        ),
        title3 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 17.sp,
            letterSpacing = (-0.5).sp,
            lineHeight = 130.sp,
        ),
        title4 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 15.sp,
            letterSpacing = (-0.5).sp,
            lineHeight = 130.sp,
        ),
        title5 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 14.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        body0 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 19.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        body1 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 15.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        body2 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 14.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        body3 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 13.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        body4 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 13.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 150.sp,
        ),
        body5 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 13.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        body6 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 11.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        body7 = TextStyle(
            fontFamily = PretendardLight,
            fontSize = 11.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        button0 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 15.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        button1 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 15.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        button2 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 15.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        button3 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 13.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        button4 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 11.sp,
            letterSpacing = (0).sp,
            lineHeight = 100.sp,
        ),
        button5 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 10.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 150.sp,
        ),
        button6 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 9.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        detail0 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 13.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        detail1 = TextStyle(
            fontFamily = PretendardRegular,
            fontSize = 13.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        detail2 = TextStyle(
            fontFamily = PretendardLight,
            fontSize = 12.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        detail3 = TextStyle(
            fontFamily = PretendardLight,
            fontSize = 11.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 120.sp,
        ),
        detail4 = TextStyle(
            fontFamily = PretendardLight,
            fontSize = 10.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        detail5 = TextStyle(
            fontFamily = PretendardLight,
            fontSize = 9.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
        calendar = TextStyle(
            fontFamily = PretendardRegular,
            fontSize = 15.sp,
            letterSpacing = (0.2).sp,
            lineHeight = 100.sp,
        ),
    )
}
