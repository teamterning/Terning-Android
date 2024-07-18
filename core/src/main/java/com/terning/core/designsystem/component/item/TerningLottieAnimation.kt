package com.terning.core.designsystem.component.item

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
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