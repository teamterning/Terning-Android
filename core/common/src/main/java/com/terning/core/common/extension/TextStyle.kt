package com.terning.core.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Composable
fun TextStyle.getFixHeightByMaxLine(maxLine: Int): Dp = with(LocalDensity.current) {
    lineHeight.toDp() * maxLine
}
