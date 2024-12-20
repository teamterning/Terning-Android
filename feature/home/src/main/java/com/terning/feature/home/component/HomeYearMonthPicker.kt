package com.terning.feature.home.component

import android.util.Log
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.util.CalendarDefaults.END_MONTH
import com.terning.core.designsystem.util.CalendarDefaults.END_YEAR
import com.terning.core.designsystem.util.CalendarDefaults.START_MONTH
import com.terning.core.designsystem.util.CalendarDefaults.START_YEAR
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import okhttp3.internal.toImmutableList

private val years =
    (START_YEAR..END_YEAR).map { "${it}년" }.toImmutableList()

private val months =
    (START_MONTH..END_MONTH).map { "${it}월" }.toImmutableList()

class PickerState {
    var selectedItem by mutableStateOf("")
}

@Composable
fun rememberPickerState() = remember { PickerState() }

@Composable
fun HomeYearMonthPicker(
    modifier: Modifier = Modifier,
    chosenYear: Int?,
    chosenMonth: Int?,
    onYearChosen: (Int?, Boolean, Boolean) -> Unit,
    onMonthChosen: (Int?, Boolean, Boolean) -> Unit,
    isYearNull: Boolean,
    isMonthNull: Boolean,
    years: List<String>,
    months: List<String>,
    isFirstNullAndFirstChange: Boolean
) {
    // todo: 변수명 바꾸기
//    val yearsWithNull = if (isYearNull) years + "-" else years
//    val monthsWithNull = if (isMonthNull) months + "-" else months

    val yearPickerState = rememberPickerState()
    val monthPickerState = rememberPickerState()

    var isFirst = isFirstNullAndFirstChange

    val startYearIndex = remember(isYearNull) {
            if (isYearNull) years.lastIndex else years.indexOf("${chosenYear ?: "-"}년")
                .takeIf { it >= 0 } ?: 0
        }

    val startMonthIndex = remember(isMonthNull) {
        if (isMonthNull) months.lastIndex else months.indexOf("${chosenMonth ?: "-"}월")
            .takeIf { it >= 0 } ?: 0
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 95.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        DatePicker(
            modifier = Modifier.weight(1f),
            pickerState = yearPickerState,
            items = years,
            startIndex = startYearIndex,
            onItemSelected = { year ->
                if (year == "-" && !isFirst) isFirst = true
                onYearChosen(
                    if (year == "-") null else year.dropLast(1).toInt(),
                    year == "-",
                    isFirst
                )
            }
        )
        Spacer(modifier = Modifier.width(18.dp))
        DatePicker(
            modifier = Modifier.weight(1f),
            pickerState = monthPickerState,
            items = months,
            startIndex = startMonthIndex,
            onItemSelected = { month ->
                if (month == "-" && !isFirst) isFirst = true
                onMonthChosen(
                    if (month == "-") null else month.dropLast(1).toInt(),
                    month == "-",
                    isFirst
                )
            }
        )
    }
}

@Composable
fun DatePicker(
    items: List<String>,
    modifier: Modifier = Modifier,
    pickerState: PickerState = rememberPickerState(),
    startIndex: Int = 0,
    visibleItemCount: Int = 3,
    onItemSelected: (String) -> Unit
) {
    var itemHeightPixel by remember { mutableIntStateOf(0) }
    val itemHeightDp = with(LocalDensity.current) { itemHeightPixel.toDp() }

    val visibleItemsMiddle = visibleItemCount / 2
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)

    LaunchedEffect(itemHeightPixel, startIndex) {
        if (itemHeightPixel > 0 && startIndex >= 0) {
            scrollState.scrollToItem(startIndex)
        }
    }

    // todo: type safety 하게 수정 필요 && 변수명도 수정
    val isNullSize = items.size == 12 || items.size == 21

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }
            .map { index ->
                var newItem = items
                if (isNullSize) newItem = items + "-"
                Log.d("PickerDebug", "Index: $index, Item: ${newItem.getOrNull(index)}")
                newItem.getOrNull(index)
            }
            .distinctUntilChanged()
            .collect { item ->
                item?.let {
                    pickerState.selectedItem = it
                    onItemSelected(it)
                    Log.d("PickerDebug", "Selected Item: $it")
                }
            }
    }

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeightDp * visibleItemCount),
            flingBehavior = flingBehavior,
            state = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(visibleItemsMiddle) {
                Spacer(modifier = Modifier.height(itemHeightDp))
            }
            items(items.size) { index ->
                val isSelected = pickerState.selectedItem == items[index]
                DatePickerContent(
                    modifier = Modifier
                        .onSizeChanged { intSize: IntSize -> itemHeightPixel = intSize.height },
                    text = items[index],
                    color = if (isSelected) Grey500 else Grey300
                )
            }
            items(visibleItemsMiddle) {
                Spacer(modifier = Modifier.height(itemHeightDp))
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .offset(y = itemHeightDp * visibleItemsMiddle)
                .padding(horizontal = 7.dp),
            color = TerningMain,
            thickness = 1.dp
        )
        HorizontalDivider(
            modifier = Modifier
                .offset(y = itemHeightDp * (visibleItemsMiddle + 1))
                .padding(horizontal = 7.dp),
            color = TerningMain,
            thickness = 1.dp
        )
    }
}

@Composable
fun DatePickerContent(
    color: Color,
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(vertical = 11.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TerningTheme.typography.title3,
            color = color,
        )
    }
}
