package com.terning.core.designsystem.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.theme.TerningPointTheme

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
) {

}

// RoundedCornerShape(8.dp),
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