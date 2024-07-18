package com.terning.core.designsystem.component.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun DateItemsPicker(
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    isYear: Boolean,
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
                Text(
                    text =
                    when (isYear) {
                        true -> if (items[index].isNotEmpty()) "${items[index]}년" else ""
                        false -> if (items[index].isNotEmpty()) "${items[index]}월" else ""
                    },
                    style = TerningTheme.typography.title3,
                    color = if (it == firstVisibleItemIndex + 1) Grey500 else Grey300,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
