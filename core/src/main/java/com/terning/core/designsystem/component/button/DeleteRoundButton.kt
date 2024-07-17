package com.terning.core.designsystem.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.White
import com.terning.core.util.NoRippleTheme

@Composable
fun DeleteRoundButton(
    style: TextStyle,
    paddingVertical: Dp,
    @StringRes text: Int,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    cornerRadius: Dp
) {
    val interactionSource = remember { MutableInteractionSource() }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            contentPadding = PaddingValues(paddingVertical),
            modifier = modifier.fillMaxWidth(),
            interactionSource = interactionSource,
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = White,
                contentColor = Grey400,
            ),
            shape = RoundedCornerShape(cornerRadius),
            onClick = { onButtonClick() },
            border = BorderStroke(width = 1.dp, color = Grey400)
        ) {
            Text(
                text = stringResource(id = text),
                style = style,
            )
        }
    }
}