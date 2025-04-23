package com.terning.feature.mypage.mypage.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.mypage.mypage.component.ToggleDefaults.thumbSize
import com.terning.feature.mypage.mypage.component.ToggleDefaults.toggleHeight
import com.terning.feature.mypage.mypage.component.ToggleDefaults.toggleHorizontalPadding
import com.terning.feature.mypage.mypage.component.ToggleDefaults.toggleWidth
import kotlin.math.roundToInt

@Composable
internal fun MyPageToggleButton(
    check: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val minBound = with(density) { 0.dp.toPx() }
    val maxBound =
        with(density) { (toggleWidth - thumbSize - toggleHorizontalPadding * 2).toPx() }
    val state by animateFloatAsState(
        targetValue = if (check) maxBound else minBound
    )
    val background = if (check) TerningMain else Grey200

    Row(
        modifier = modifier
            .padding(end = 7.dp)
            .clip(CircleShape)
            .height(toggleHeight)
            .width(toggleWidth)
            .background(background)
            .noRippleClickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .padding(horizontal = toggleHorizontalPadding)
                .offset { IntOffset(state.roundToInt(), 0) }
                .size(thumbSize)
                .clip(CircleShape)
                .background(White)
        )
    }
}

private object ToggleDefaults {
    val toggleHeight: Dp = 20.dp
    val toggleWidth: Dp = 34.dp
    val thumbSize: Dp = 18.dp
    val toggleHorizontalPadding: Dp = 1.dp
}

@Composable
@Preview(showBackground = true)
private fun MyPageToggleButtonPreview() {
    TerningPointTheme {
        MyPageToggleButton(
            check = true,
            onClick = {}
        )
    }
}