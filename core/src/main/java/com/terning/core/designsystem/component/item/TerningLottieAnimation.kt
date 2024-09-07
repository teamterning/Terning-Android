package com.terning.core.designsystem.component.item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun TerningLottieAnimation(
    jsonString: String,
    modifier: Modifier = Modifier
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.Asset(jsonString))
    LottieAnimation(
        modifier = modifier.clip(RectangleShape),
        composition = lottieComposition,
        clipToCompositionBounds = false
    )
}