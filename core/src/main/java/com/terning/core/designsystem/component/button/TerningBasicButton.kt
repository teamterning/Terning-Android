package com.terning.core.designsystem.component.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningMain2
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.util.NoRippleTheme

@Composable
fun TerningBasicButton(
    modifier: Modifier = Modifier,
    text: String = "",
    shape: Shape,
    style: TextStyle,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) TerningMain2 else TerningMain

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            modifier = modifier.fillMaxWidth(),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = White
            ),
            shape = shape,
            onClick = { onClick() }
        ) {
            Text(
                text = text,
                style = style
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TerningBasicButtonPreview() {
    TerningPointTheme {
        TerningBasicButton(
            text = "Button",
            shape = ButtonDefaults.shape,
            style = TerningTheme.typography.button0,
            onClick = {}
        )
    }
}
