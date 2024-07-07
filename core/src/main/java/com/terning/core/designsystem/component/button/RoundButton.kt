package com.terning.core.designsystem.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    style: TextStyle,
    paddingVertical: Dp,
    cornerRadius: Dp,
    @StringRes text: Int,
    isEnabled: Boolean = true,
    onButtonClick: () -> Unit,
) {
    TerningBasicButton(
        modifier = modifier,
        style = style,
        paddingVertical = paddingVertical,
        shape = RoundedCornerShape(cornerRadius),
        text = text,
        isEnabled = isEnabled,
        onButtonClick = onButtonClick,
    )
}

@Preview(showBackground = true)
@Composable
fun RoundButtonPreview() {
    TerningPointTheme {
        RoundButton(
            style = TerningTheme.typography.button0,
            text = R.string.button_preview,
            paddingVertical = 19.dp,
            cornerRadius = 10.dp,
            onButtonClick = {}
        )
    }
}