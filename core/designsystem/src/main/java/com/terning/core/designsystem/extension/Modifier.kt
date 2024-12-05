package com.terning.core.designsystem.extension

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey200

@SuppressLint("ModifierFactoryUnreferencedReceiver")
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Modifier.addFocusCleaner(focusManager: FocusManager): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }
}

@Composable
fun Modifier.customShadow(
    color: Color = Grey200,
    shadowRadius: Dp = 0.dp,
    shadowWidth: Dp = 2.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
) = composed {
    val paint: Paint = remember { Paint() }
    val density = LocalDensity.current
    val radius = shadowRadius + shadowWidth
    val blurRadiusPx = with(density) { radius.toPx() }
    val maskFilter = remember {
        BlurMaskFilter(blurRadiusPx, BlurMaskFilter.Blur.NORMAL)
    }
    drawBehind {
        drawIntoCanvas { canvas ->
            val frameworkPaint = paint.asFrameworkPaint()
            if (radius != 0.dp) {
                frameworkPaint.maskFilter = maskFilter
            }
            frameworkPaint.color = color.toArgb()

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()
            val rightPixel = size.width + leftPixel
            val bottomPixel = size.height + topPixel

            if (radius > 0.dp) {
                val radiusPx = radius.toPx()
                canvas.drawRoundRect(
                    left = leftPixel,
                    top = topPixel,
                    right = rightPixel,
                    bottom = bottomPixel,
                    radiusX = radiusPx,
                    radiusY = radiusPx,
                    paint = paint,
                )
            } else {
                canvas.drawRect(
                    left = leftPixel,
                    top = topPixel,
                    right = rightPixel,
                    bottom = bottomPixel,
                    paint = paint,
                )
            }
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver", "ReturnFromAwaitPointerEventScope")
fun Modifier.swipableVertically(
    setHideComponent: () -> Unit
) = composed {
    var initialTouchPosition by remember { mutableStateOf<Offset?>(null) }
    pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()
                val position = event.changes.first().position

                if (event.changes.first().pressed) {
                    if (initialTouchPosition == null) {
                        initialTouchPosition = position
                    } else {
                        val deltaY = initialTouchPosition?.let { position.y - it.y }
                        if (deltaY != null && deltaY > 300f) {
                            setHideComponent()
                        }
                    }
                } else {
                    initialTouchPosition = null
                }
            }
        }
    }
}
