package com.terning.core.designsystem.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White

@Composable
fun TerningBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    textColor: Color,
    hintColor: Color,
    leftIconColor: Color,
    drawLineColor: Color,
    helperColor: Color,
    cursorBrush: Brush,
    strokeWidth: Float = 1f,
    leftIcon: Int? = null,
    maxTextLength: Int? = null,
    showTextLength: Boolean = false,
    hint: String = "",
    helperMessage: String = "",
    helperIcon: Int? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BasicTextField(value = value,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            focusManager.clearFocus()
        }),

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
                        style = TerningTheme.typography.button3,
                        color = Grey300,
                    )
                }
            }
        })

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
            style = TerningTheme.typography.detail3,
            color = helperColor,
        )
    }
}