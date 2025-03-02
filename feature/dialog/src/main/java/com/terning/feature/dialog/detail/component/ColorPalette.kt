package com.terning.feature.dialog.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.item.RadioButtonGroups
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.type.ColorType
import com.terning.featrue.dialog.R


@Composable
fun ColorPalette(
    selectedColor: ColorType,
    onColorSelected: (ColorType) -> Unit,
    modifier: Modifier = Modifier
) {
    RadioButtonGroups(
        options = ColorType.entries,
        gridCellCount = ColorType.entries.size,
        onOptionSelected = onColorSelected,
        buttonComposable = { colorType, isSelected, onOptionSelected ->
            ColorButton(
                colorType = colorType,
                isSelected = selectedColor == colorType,
                onColorSelected = onOptionSelected
            )
        },
        verticalArrangementSpace = 0.dp,
        horizontalArrangementSpace = 0.dp,
        modifier = modifier.wrapContentSize()
    )
}

@Composable
internal fun ColorButton(
    colorType: ColorType,
    isSelected: Boolean,
    onColorSelected: (ColorType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val externalColor = if (isSelected) colorType.sub else Color.Transparent
    val externalBoxModifier = Modifier
        .size(38.dp)
        .background(
            shape = CircleShape,
            color = externalColor
        )
    val internalBoxModifier = Modifier
        .padding(4.dp)
        .then(
            if (isSelected)
                Modifier.border(
                    width = 2.dp,
                    color = colorType.border,
                    shape = CircleShape,
                )
            else Modifier
        )
        .background(
            shape = CircleShape,
            color = colorType.main
        )


    Box(
        modifier = modifier.wrapContentSize(),
    ) {
        Box(
            modifier = Modifier
                .noRippleClickable {
                    onColorSelected(colorType)
                }
                .then(externalBoxModifier)
                .then(internalBoxModifier),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Image(
                    painter = painterResource(id = R.drawable.ic_color_check),
                    contentDescription = "color selected"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorPalettePreview() {
    ColorPalette(
        selectedColor = ColorType.RED,
        onColorSelected = {}
    )
}
