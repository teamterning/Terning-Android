package com.terning.feature.filtering.filtering.component

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
import com.kez.picker.PickerState
import com.kez.picker.rememberPickerState
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun YearMonthPicker(
    years: List<String>,
    months: List<String>,
    modifier: Modifier = Modifier,
    yearPickerState: PickerState = rememberPickerState(),
    monthPickerState: PickerState = rememberPickerState(),
    startYearIndex: Int = 5,
    startMonthIndex: Int = 2,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(95.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DatePicker(
            modifier = Modifier.weight(1f),
            pickerState = yearPickerState,
            items = years,
            startIndex = startYearIndex,
        )
        Spacer(modifier = Modifier.width(25.dp))
        DatePicker(
            modifier = Modifier.weight(1f),
            pickerState = monthPickerState,
            items = months,
            startIndex = startMonthIndex,
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
) {
    var itemHeightPixel by remember { mutableIntStateOf(0) }
    val itemHeightDp = with(LocalDensity.current) { itemHeightPixel.toDp() }

    val visibleItemsMiddle = visibleItemCount / 2
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)

    LaunchedEffect(itemHeightPixel) {
        if (itemHeightPixel > 0) scrollState.scrollToItem(startIndex)
    }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }
            .map { index -> items.getOrNull(index) }
            .distinctUntilChanged()
            .collect { item ->
                if (item != null) {
                    pickerState.selectedItem = item
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
                DatePickerContent(
                    modifier = Modifier
                        .onSizeChanged { intSize: IntSize -> itemHeightPixel = intSize.height },
                    text = items[index],
                    color = if (pickerState.selectedItem == items[index]) Grey500 else Grey300
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