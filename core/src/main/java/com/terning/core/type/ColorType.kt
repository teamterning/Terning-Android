package com.terning.core.type

import androidx.compose.ui.graphics.Color
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalBlueLi
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPinkLi
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalPurpleLi
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalRedLi

enum class ColorType(
    main: Color,
    sub: Color
) {
    RED(main = CalRed, sub = CalRedLi),
    ORANGE(main = CalOrange2, sub = CalRedLi),
    GREEN(main = CalGreen2, sub = CalRedLi),
    BLUE(main = CalBlue2, sub = CalBlueLi),
    PURPLE(main = CalPurple, sub = CalPurpleLi),
    PINK(main = CalPink, sub = CalPinkLi)
}