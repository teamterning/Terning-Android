package com.terning.core.designsystem.component.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    text: String = "",
    style: TextStyle,
    paddingVertical: Dp,
    onButtonClick: () -> Unit,
) {
    TerningBasicButton(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        onButtonClick = onButtonClick,
        text = text,
        style = style,
        paddingVertical = paddingVertical
    )
}

@Preview(showBackground = true)
@Composable
fun RectangleButtonPreview() {
    TerningPointTheme {
        RectangleButton(
            style = TerningTheme.typography.button0,
            text = "RectangleButton",
            paddingVertical = 19.dp,
            onButtonClick = {}
        )
    }
}