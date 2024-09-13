package com.terning.feature.dialog.detail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey375
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningSub5
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.util.NoRippleTheme
import com.terning.feature.R

@Composable
fun ScrapColorChangeButton(
    isColorChanged: Boolean,
    cornerRadius: Dp,
    paddingVertical: Dp,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = when {
        isColorChanged && !isPressed -> White
        isColorChanged && isPressed -> TerningSub5
        else -> White
    }
    val textColor = when {
        isColorChanged -> TerningMain
        else -> Grey375
    }
    val borderColor = when {
        isColorChanged -> TerningMain
        else -> Grey150
    }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Button(
            contentPadding = PaddingValues(vertical = paddingVertical),
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = textColor,
            ),
            border = BorderStroke(
                width = 1.dp,
                color = borderColor
            ),
            shape = RoundedCornerShape(cornerRadius),
            onClick = onButtonClick
        ) {
            Text(
                text = stringResource(id = R.string.dialog_content_calendar_color_change),
                style = TerningTheme.typography.button3,
                textAlign = TextAlign.Center,
            )
        }
    }
}
