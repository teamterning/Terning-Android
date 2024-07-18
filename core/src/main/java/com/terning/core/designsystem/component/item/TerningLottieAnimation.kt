package com.terning.core.designsystem.component.item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
        modifier = modifier,
        composition = lottieComposition
    )
}