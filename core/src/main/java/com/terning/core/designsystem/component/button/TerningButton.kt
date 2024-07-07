package com.terning.core.designsystem.component.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningMain2
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun TerningButton() {
//    val interactionSource by remember { MutableInteractionSource() }
//    val isPressed by interactionSource.collectIsPressedAsState()
//    val backgroundColor = if (isPressed) TerningMain else TerningMain2
//
//    Button(
//        onClick = {},
//        interactionSource = interactionSource,
//        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
//    ) {
//        Text("ButtonSample")
//    }
}

@Preview(showBackground = true)
@Composable
fun TerningButtonPreview() {
    TerningTheme {
        TerningButton()
    }
}
