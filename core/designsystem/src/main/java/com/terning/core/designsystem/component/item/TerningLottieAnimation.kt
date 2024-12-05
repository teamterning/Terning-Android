package com.terning.core.designsystem.component.item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * 로티애니메이션을 표시하기 위한 컴포넌트입니다.
 *
 * @param jsonFile 로티애니메이션의 JSON 문자열로, raw 폴더에 있는 JSON 파일의 이름입니다.
 * @param modifier 수정자입니다.
 * @param iterations 반복 횟수입니다. 기본값은 1회입니다.
 */

@Composable
fun TerningLottieAnimation(
    jsonFile: Int,
    modifier: Modifier = Modifier,
    iterations: Int = 1,
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(jsonFile))
    LottieAnimation(
        modifier = modifier.clip(RectangleShape),
        composition = lottieComposition,
        iterations = iterations,
        clipToCompositionBounds = false
    )
}