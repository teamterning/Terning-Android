package com.terning.feature.home.changefilter.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.button.ChangeFilterButton
import com.terning.feature.R
import com.terning.feature.home.home.model.InternFilterData

@Composable
fun ChangeFilteringRadioGroup(
    filterType: Int,
    internFilterData: InternFilterData?,
    onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val options = if (filterType == 0) {
        listOf(
            R.string.filtering_status1_button1,
            R.string.filtering_status1_button2,
            R.string.filtering_status1_button3,
            R.string.filtering_status1_button4,
        )
    } else {
        listOf(
            R.string.filtering_status2_button1,
            R.string.filtering_status2_button2,
            R.string.filtering_status2_button3,
        )
    }

    val selectedIndex = remember { mutableIntStateOf(0) }
    var selectedButton = remember { mutableStateListOf(false, false, false, false) }

    if (filterType == 0 && internFilterData?.grade != null) {
        selectedButton[internFilterData.grade] = true
    } else if(filterType == 1 && internFilterData?.workingPeriod != null) {
        selectedButton[internFilterData.workingPeriod] = true
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(options.size),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp)

    ) {
        itemsIndexed(options) { index, option ->
            ChangeFilterButton(
                isSelected = selectedButton[index],
                modifier = Modifier
                    .wrapContentHeight(),
                text = options[index],
                cornerRadius = 10.dp,
                paddingVertical = 10.dp,
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