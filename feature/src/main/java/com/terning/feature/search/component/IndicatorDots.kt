package com.terning.feature.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.White

@Composable
fun IndicatorDots(
    isSelected: Boolean, modifier: Modifier,
) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .clip(CircleShape)
            .size(6.dp)
            .background(if (isSelected) TerningMain else White)
    )
}