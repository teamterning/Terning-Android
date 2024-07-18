package com.terning.feature.home.changefilter.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.button.ChangeFilterButton

@Composable
fun ChangeFilteringRadioGroup(
    optionList: List<Int>,
    initOption: Int,
    onButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember { mutableIntStateOf(initOption) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(optionList.size),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp)

    ) {
        itemsIndexed(optionList) { index, option ->
            ChangeFilterButton(
                isSelected = selectedIndex == index,
                modifier = Modifier
                    .wrapContentHeight(),
                text = option,
                cornerRadius = 10.dp,
                paddingVertical = 10.dp,
                onButtonClick = {
                    selectedIndex = index
                    onButtonClick(index)
                }
            )
        }
    }
}