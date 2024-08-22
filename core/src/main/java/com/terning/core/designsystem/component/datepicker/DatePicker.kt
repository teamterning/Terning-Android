package com.terning.core.designsystem.component.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import okhttp3.internal.toImmutableList

private const val START_YEAR = 2023
private const val END_YEAR = 2025
private const val START_MONTH = 1
private const val END_MONTH = 12
private val years =
    (listOf("") + (START_YEAR..END_YEAR).map { it.toString() } + listOf("") + listOf("")).toImmutableList()
private val monthsNumber =
    (listOf("") + (START_MONTH..END_MONTH).map { it.toString() } + listOf("") + listOf("")).toImmutableList()

/**
 * 데이트 피커를 나타냅니다.
 *
 * 사용자가 원하는 연도와 월을 설정할 수 있습니다.
 *
 * @param chosenYear 선택된 연도를 나타내는 값입니다.
 * @param chosenMonth 선택된 월을 나타내는 값입니다.
 * @param onYearChosen 연도가 선택되었을 때 호출되는 콜백 함수입니다.
 * @param onMonthChosen 월이 선택되었을 때 호출되는 콜백 함수입니다.
 */
@Composable
fun DatePickerUI(
    chosenYear: Int,
    chosenMonth: Int,
    onYearChosen: (Int) -> Unit = {},
    onMonthChosen: (Int) -> Unit = {},
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        DateSelectionSection(
            chosenYear = chosenYear,
            chosenMonth = chosenMonth,
            onYearChosen = { onYearChosen(it.toInt()) },
            onMonthChosen = { onMonthChosen(it.toInt()) },
        )
    }
}

/**
 * 연도와 월을 선택할 수 있는 두 개의 피커를 제공합니다.
 *
 * @param chosenYear 선택된 연도를 나타내는 값입니다.
 * @param chosenMonth 선택된 월을 나타내는 값입니다.
 * @param onYearChosen 연도가 선택되었을 때 호출되는 콜백 함수입니다.
 * @param onMonthChosen 월이 선택되었을 때 호출되는 콜백 함수입니다.
 */
@Composable
fun DateSelectionSection(
    chosenYear: Int,
    chosenMonth: Int,
    onYearChosen: (String) -> Unit,
    onMonthChosen: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        DateItemsPicker(
            items = years.toImmutableList(),
            firstIndex = (chosenYear - START_YEAR),
            onItemSelected = onYearChosen,
            isYear = true
        )
        Spacer(modifier = Modifier.width(25.dp))
        DateItemsPicker(
            items = monthsNumber.toImmutableList(),
            firstIndex = chosenMonth,
            onItemSelected = onMonthChosen,
            isYear = false
        )
    }
}

/**
 * 사용자가 스크롤을 통해 연도 또는 월을 선택할 수 있는 피커입니다.
 *
 * @param items 선택 가능한 항목들의 리스트입니다. (연도 또는 월)
 * @param firstIndex 초기 선택된 항목의 인덱스입니다. (연도 또는 월)
 * @param onItemSelected 항목이 선택되었을 때 호출되는 콜백 함수입니다.
 * @param modifier 레이아웃, 동작 등을 수정할 수 있는 Modifier 옵션입니다.
 * @param isYear 이 피커가 연도를 선택하는 것인지에 대한 여부입니다.
 */
@Composable
fun DateItemsPicker(
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    isYear: Boolean
) {
    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(!listState.isScrollInProgress) {
        onItemSelected(currentValue.value)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Box(
        modifier = Modifier.height(108.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HorizontalDivider(
                modifier = Modifier.size(height = 1.dp, width = 71.dp),
                color = TerningMain
            )
            HorizontalDivider(
                modifier = Modifier.size(height = 1.dp, width = 71.dp),
                color = TerningMain
            )
        }
        LazyColumn(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
        ) {
            items(items.size) {
                val index = it % items.size
                val firstVisibleItemIndex by remember {
                    derivedStateOf { listState.firstVisibleItemIndex }
                }
                if (it == firstVisibleItemIndex + 1) {
                    currentValue.value = items[index]
                }
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text =
                    when (isYear) {
                        true -> if (items[index].isNotEmpty()) "${items[index]}년" else ""
                        false -> if (items[index].isNotEmpty()) "${items[index]}월" else ""
                    },
                    style = TerningTheme.typography.title3,
                    color = if (it == firstVisibleItemIndex + 1) Grey500 else Grey300,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
    }
}