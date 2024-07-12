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
import androidx.compose.runtime.setValue
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
private val years =
    (listOf("") + (START_YEAR..END_YEAR).map { it.toString() } + listOf("")).toImmutableList()
private val monthsNumber =
    (listOf("") + (1..12).map { it.toString() } + listOf("")).toImmutableList()

@Composable
fun DatePickerUI(
    chosenYear: Int,
    chosenMonth: Int,
    onYearChosen: (Int) -> Unit = {},
    onMonthChosen: (Int) -> Unit = {},
    onScrolledOccurred: (Boolean) -> Unit = {}
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        DateSelectionSection(
            chosenYear = chosenYear,
            chosenMonth = chosenMonth,
            onYearChosen = { onYearChosen(it.toInt()) },
            onMonthChosen = { onMonthChosen(it.toInt()) },
            onScrolledOccurred = onScrolledOccurred
        )
    }
}

@Composable
fun DateSelectionSection(
    chosenYear: Int,
    chosenMonth: Int,
    onYearChosen: (String) -> Unit,
    onMonthChosen: (String) -> Unit,
    onScrolledOccurred: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        DateItemsPicker(
            items = years.toImmutableList(),
            firstIndex = (chosenYear - START_YEAR),
            onItemSelected = onYearChosen,
            onScrolledOccurred = onScrolledOccurred
        )
        Spacer(modifier = Modifier.width(10.dp))
        DateItemsPicker(
            items = monthsNumber.toImmutableList(),
            firstIndex = chosenMonth,
            onItemSelected = onMonthChosen,
            onScrolledOccurred = onScrolledOccurred
        )
    }
}

@Composable
fun DateItemsPicker(
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
    onScrolledOccurred: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }
    var isScrolled by remember { mutableStateOf(false) }

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            onItemSelected(currentValue.value)
            listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
        } else {
            isScrolled = true
            onScrolledOccurred(true)
        }
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
                modifier = Modifier.size(height = 1.dp, width = 60.dp),
                color = TerningMain
            )
            HorizontalDivider(
                modifier = Modifier.size(height = 1.dp, width = 60.dp),
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
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = items[index],
                    style = TerningTheme.typography.title3,
                    color = if (it == firstVisibleItemIndex + 1) Grey500 else Grey300,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}
