package com.terning.core.designsystem.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTypography
import com.terning.core.designsystem.theme.White

@Composable
fun TerningTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    textColor: Color = TerningMain,
    cursorBrush: Brush = SolidColor(TerningMain),
    hint: String = "",
    hintColor: Color = TerningMain,
    strokeWidth: Float = 1f,
    drawLineColor: Color,
    leftIcon: Int? = null,
    leftIconColor: Color = TerningMain,
    maxTextLength: Int? = null,
    showTextLength: Boolean = false,
    helperMessage: String = "",
    helperIcon: Int? = null,
    helperColor: Color = TerningMain,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,

        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .drawWithContent {
                val canvasWidth = size.width
                val canvasHeight = size.height

                drawContent()
                drawLine(
                    color = drawLineColor,
                    start = Offset(x = 0f, y = canvasHeight),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    strokeWidth = strokeWidth.dp.toPx(),
                )
            },

        textStyle = textStyle.copy(color = textColor),
        cursorBrush = cursorBrush,

        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                leftIcon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        tint = leftIconColor,
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            style = textStyle,
                            color = hintColor
                        )
                    }
                    innerTextField()
                }
                if (showTextLength && maxTextLength != null) {
                    Text(
                        text = "${value.length}/$maxTextLength",
                        style = TerningTypography().button3,
                        color = Grey300,
                    )
                }
            }
        }
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        helperIcon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = helperColor,
            )
        }
        Text(
            text = helperMessage,
            style = TerningTypography().detail3,
            color = helperColor,
        )
    }
}