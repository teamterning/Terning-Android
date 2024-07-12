package com.terning.core.designsystem.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun <T> RadioButtonGroups(
    options: List<T>,
    buttonComposable: @Composable (T, Boolean, (T) -> Unit) -> Unit,
    gridCellCount: Int,
    verticalArrangementSpace: Dp,
    horizontalArrangementSpace: Dp,
    modifier: Modifier = Modifier,
    onOptionSelected: (T) -> Unit = {},

) {
    var selectedOption by remember { mutableStateOf(options[0]) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridCellCount),
        verticalArrangement = Arrangement.spacedBy(verticalArrangementSpace),
        horizontalArrangement = Arrangement.spacedBy(horizontalArrangementSpace),
        modifier = modifier
    ) {
        items(options) { option ->
            buttonComposable(
                option,
                (selectedOption == option),
            ) {
                selectedOption = option
                onOptionSelected(option)
            }
        }
    }
}

