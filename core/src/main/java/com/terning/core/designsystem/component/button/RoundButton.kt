package com.terning.core.designsystem.component.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.TerningPointTheme

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
) {
    TerningBasicButton(shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        text = text)
}

@Preview(showBackground = true)
@Composable
fun RoundButtonPreview() {
    TerningPointTheme {
        RoundButton(
            text = "RoundButton",
            onClick = {}
        )
    }
}