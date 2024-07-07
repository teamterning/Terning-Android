package com.terning.core.designsystem.component.button

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TerningBasicButton() {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) Color.Blue else Color.Red

    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 6.dp, bottom = 6.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = {}
            )
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    if (isPressed) {
                        drawRoundRect(
                            color = color,
                            blendMode = BlendMode.SrcOver,
                            cornerRadius = CornerRadius(20f)
                        )
                    }
                }
            },
        shape = RoundedCornerShape(12.dp),
    ) {
        Text("hi")
    }
}

