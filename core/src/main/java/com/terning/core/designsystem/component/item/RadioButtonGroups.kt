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

/**
 * 라디오버튼 그룹을 관리하기 위한 컴포넌트입니다.
 *
 * @param options 라디오버튼에 표시될 옵션 목록입니다.
 * @param buttonComposable 라디오버튼을 표시하는 컴포저블입니다.
 * @param gridCellCount Row 하나에 들어갈 버튼 개수입니다.
 * @param verticalArrangementSpace Row 간격입니다.
 * @param horizontalArrangementSpace Column 간격입니다.
 * @param modifier 수정자입니다.
 * @param onOptionSelected 옵션이 선택되었을 때 호출되는 콜백입니다.
 */

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

