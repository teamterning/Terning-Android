package com.terning.core.designsystem.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.CalBlue1
import com.terning.core.designsystem.theme.CalBlue2
import com.terning.core.designsystem.theme.CalGreen1
import com.terning.core.designsystem.theme.CalGreen2
import com.terning.core.designsystem.theme.CalOrange1
import com.terning.core.designsystem.theme.CalOrange2
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.CalRed
import com.terning.core.designsystem.theme.CalYellow
import com.terning.core.extension.noRippleClickable

@Composable
fun ColorPalette(
    modifier: Modifier = Modifier,
    onColorSelected:(Color) -> Unit = {}
) {
    val colorList = listOf(
        CalRed,
        CalOrange2,
        CalGreen1,
        CalBlue1,
        CalPurple,
        CalOrange1,
        CalYellow,
        CalGreen2,
        CalBlue2,
        CalPink
    )

    RadioButtonGroups(
        modifier = modifier.size(251.dp, 84.dp),
        options = colorList,
        gridCellCount = 5,
        onOptionSelected = { color ->
            onColorSelected(color)
        },
        buttonComposable = {
            color, isSelected, onOptionSelected ->
            ColorButton(
                color = color,
                isSelected = isSelected,
                onColorSelected = onOptionSelected)
        },
        verticalArrangementSpace = 6.dp,
        horizontalArrangementSpace = 14.dp,
    )
}

@Composable
internal fun ColorButton(
    modifier: Modifier = Modifier,
    color: Color,
    isSelected: Boolean,
    onColorSelected: (Color) -> Unit
) {
    Box(modifier = modifier.size(39.dp)) {
        Box(
            modifier = Modifier
                .size(39.dp)
                .background(shape = CircleShape, color = color)
                .noRippleClickable {
                    onColorSelected(color)
                },
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Image(
                    painter = painterResource(id = R.drawable.ic_color_check),
                    contentDescription = ""
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ColorPalettePreview() {
    ColorPalette()
}
