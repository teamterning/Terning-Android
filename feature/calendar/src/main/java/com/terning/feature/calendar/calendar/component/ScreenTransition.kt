package com.terning.feature.calendar.calendar.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.terning.feature.calendar.R

/**
 * 두 화면 간 전환을 담당하는 컴포넌트
 *
 * @param targetState 전환할 상태
 * @param transitionOne 첫번째 화면에서 두번쨰 화면으로 이동할 때 발생할 전환 모션
 * @param transitionTwo 두번째 화면에서 첫번째 화면으로 이동할 때 발생할 전환 모션
 * @param contentOne 첫번째 화면
 * @param contentTwo 두번째 화면
 */

@Composable
fun ScreenTransition(
    targetState: Boolean,
    transitionOne: ContentTransform,
    transitionTwo: ContentTransform,
    contentOne: @Composable () -> Unit,
    contentTwo: @Composable () -> Unit
) {
    AnimatedContent(
        targetState = targetState,
        transitionSpec = {
            if (targetState) {
                transitionOne
            } else {
                transitionTwo
            }.using(
                sizeTransform = SizeTransform(clip = true)
            )
        },
        label = stringResource(id = R.string.calendar_animation_label)
    ) { state ->
        if (state) {
            contentOne.invoke()
        } else {
            contentTwo.invoke()
        }
    }
}