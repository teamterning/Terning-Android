package com.terning.core.designsystem.component.textfield

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White

@Composable
fun TerningBasicTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier,
    textStyle: TextStyle,
    textColor: Color,
    hintColor: Color,
    drawLineColor: Color,
    cursorBrush: Brush,
    helperColor: Color,
    strokeWidth: Float = 1f,
    leftIcon: Int? = null,
    leftIconColor: Color = TerningMain,
    imeAction: ImeAction = ImeAction.Done,
    maxTextLength: Int? = null,
    showTextLength: Boolean = false,
    hint: String = "",
    helperMessage: String = "",
    helperIcon: Int? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onDoneAction: () -> Unit? = {},
    onSearchAction: () -> Unit? = {},
) {
    val isFocused: MutableState<Boolean> = remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = {
            if (maxTextLength == null || it.length <= maxTextLength + 1) {
                onValueChange(it)
            }
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onDone = { onDoneAction() },
            onSearch = { onSearchAction() },
        ),

        modifier = modifier
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
            }
            .onFocusChanged {
                isFocused.value = it.isFocused
            },

        textStyle = textStyle.copy(color = textColor),
        cursorBrush = cursorBrush,

        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
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
                            color = if (isFocused.value) hintColor else textColor
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
        },

        enabled = enabled,
        readOnly = readOnly,
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(top = 5.dp)
    ) {
        helperIcon?.let {
            Icon(
                painter = painterResource(id = helperIcon),
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