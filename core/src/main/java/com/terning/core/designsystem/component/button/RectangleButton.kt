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
    style: TextStyle,
    paddingVertical: Dp,
    isEnabled: Boolean = true,
    text: String = "",
    onButtonClick: () -> Unit,
) {
    TerningBasicButton(
        modifier = modifier,
        style = style,
        paddingVertical = paddingVertical,
        isEnabled = isEnabled,
        text = text,
        shape = RoundedCornerShape(0.dp),
        onButtonClick = onButtonClick,
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