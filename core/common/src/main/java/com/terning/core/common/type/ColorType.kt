package com.terning.core.common.type

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalBlueBc
import com.terning.core.designsystem.theme.CalBlueLi
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalGreenBc
import com.terning.core.designsystem.theme.CalGreenLi
import com.terning.core.designsystem.theme.CalOrange2
import com.terning.core.designsystem.theme.CalOrangeBc
import com.terning.core.designsystem.theme.CalOrangeLi
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPinkBc
import com.terning.core.designsystem.theme.CalPinkLi
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalPurpleBc
import com.terning.core.designsystem.theme.CalPurpleLi
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalRedBc
import com.terning.core.designsystem.theme.CalRedLi

enum class ColorType(
    val typeName: String,
    val main: Color,
    val border: Color,
    val sub: Color
) {
    RED(typeName = "red", main = CalRed, border = CalRedLi, sub = CalRedBc),
    ORANGE(typeName = "orange", main = CalOrange2, border = CalOrangeLi, sub = CalOrangeBc),
    GREEN(typeName = "green", main = CalGreen2, border = CalGreenLi, sub = CalGreenBc),
    BLUE(typeName = "blue", main = CalBlue2, border = CalBlueLi, sub = CalBlueBc),
    PURPLE(typeName = "purple", main = CalPurple, border = CalPurpleLi, sub = CalPurpleBc),
    PINK(typeName = "pink", main = CalPink, border = CalPinkLi, sub = CalPinkBc);

    companion object {
        fun findColorType(mainColor: Color): ColorType? = entries.find { it.main == mainColor }
    }
}