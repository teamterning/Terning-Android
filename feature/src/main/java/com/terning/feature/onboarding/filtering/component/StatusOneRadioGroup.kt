package com.terning.feature.onboarding.filtering.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.feature.R

@Composable
fun StatusOneRadioGroup(
    onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = listOf(
        R.string.filtering_status1_button1,
        R.string.filtering_status1_button2,
        R.string.filtering_status1_button3,
        R.string.filtering_status1_button4
    )

    val selectedOptions = listOf(
        R.string.filtering_status1_description1,
        R.string.filtering_status1_description2,
        R.string.filtering_status1_description3,
        R.string.filtering_status1_description4
    )

    val selectedIndex = remember { mutableIntStateOf(options[0]) }
    val selectedButton = remember { mutableStateListOf(false, false, false, false) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        itemsIndexed(options) { index, option ->

            FilteringButton(
                isSelected = selectedButton[index],
                modifier = modifier.fillMaxWidth(),
                text = if (selectedButton[index]) selectedOptions[index] else option,
                onButtonClick = {
                    selectedIndex.intValue = option
                    selectedButton.indices.forEach { i -> selectedButton[i] = false }
                    selectedButton[index] = true
                    onButtonClick(index)
                }
            )
        }
    }
}
