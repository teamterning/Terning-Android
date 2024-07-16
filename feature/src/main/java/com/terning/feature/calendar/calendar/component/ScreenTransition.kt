package com.terning.feature.calendar.calendar.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.terning.feature.R

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
        if(state) {
            contentOne.invoke()
        } else {
            contentTwo.invoke()
        }
    }
}