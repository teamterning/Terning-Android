package com.terning.core.designsystem.component.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import okhttp3.internal.immutableListOf
import com.terning.core.R
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable
import okhttp3.internal.toImmutableList

@Composable
fun DatePicker(
    value: String,
    placeHolder: String,
    borderColor: Color,
    isEditable: Boolean,
    onClicked: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val isEmpty by remember(value) { derivedStateOf { value.isEmpty() } }

    val newModifier = modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 39.dp)
        .clip(RoundedCornerShape(9.dp))
        .then(
            if (isEmpty || !isEditable) {
                Modifier
            } else {
                Modifier.border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(10.dp)
                )
            }
        )

    val backgroundColor = remember(isEmpty, isEditable) {
        if (isEmpty || !isEditable) {
            Grey150
        } else {
            White
        }
    }

    Box(
        modifier = newModifier
            .background(backgroundColor, RoundedCornerShape(9.dp))
            .noRippleClickable { if (isEditable) onClicked() }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(),
                text = if (isEmpty) placeHolder else value,
                color = if (isEmpty) Color.Red else Color.Blue,
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_right),
                contentDescription = "date picker icon",
                tint = Color.Green
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPickerBottomSheet(onSelected: (String) -> Unit, onDismissRequest: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()

    val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    var chosenYear by remember { mutableIntStateOf(currentYear) }
    var chosenMonth by remember { mutableIntStateOf(currentMonth) }
    var chosenDay by remember { mutableIntStateOf(currentDay) }

    val isValidDate by remember { derivedStateOf { calculateValidDate(chosenYear, chosenMonth, chosenDay) } }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        DatePickerUI(
            isValidDate = isValidDate,
            chosenYear = chosenYear,
            chosenMonth = chosenMonth,
            chosenDay = chosenDay,
            onYearChosen = { chosenYear = it },
            onMonthChosen = { chosenMonth = it },
            onDayChosen = { chosenDay = it }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(
                    color = if (isValidDate) Black else Color.Red,
                    shape = RoundedCornerShape(9.dp)
                ),
            onClick =  {
                if (isValidDate) {
                    val calendar = Calendar.getInstance().apply {
                        set(chosenYear, chosenMonth - 1, chosenDay)
                    }
                    val formattedDate = formatter.format(calendar.time)

                    onSelected(formattedDate)
                }
            }
        )
        {
            Text(text = "확인")
        }
        Spacer(modifier = Modifier.height(60.dp))
    }
}

fun calculateValidDate(year: Int, month: Int, day: Int): Boolean {
    val givenCalendar = Calendar.getInstance().apply {
        set(year, month - 1, day)
    }
    val referenceCalendar = Calendar.getInstance()

    return !givenCalendar.after(referenceCalendar)
}

@Composable
fun DatePickerUI(
    isValidDate: Boolean,
    chosenYear: Int,
    chosenMonth: Int,
    chosenDay: Int,
    onYearChosen: (Int) -> Unit,
    onMonthChosen: (Int) -> Unit,
    onDayChosen: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        DateSelectionSection(
            isValidDate = isValidDate,
            chosenYear = chosenYear,
            chosenMonth = chosenMonth,
            chosenDay = chosenDay,
            onYearChosen = { onYearChosen(it.toInt()) },
            onMonthChosen = { onMonthChosen(it.toInt()) },
            onDayChosen = { onDayChosen(it.toInt()) },
        )
    }
}

@Composable
fun DateSelectionSection(
    isValidDate: Boolean,
    chosenYear: Int,
    chosenMonth: Int,
    chosenDay: Int,
    onYearChosen: (String) -> Unit,
    onMonthChosen: (String) -> Unit,
    onDayChosen: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        DateItemsPicker(
            isValidDate = isValidDate,
            max = currentYear - 1950,
            items = years.toImmutableList(),
            firstIndex = (chosenYear - START_YEAR),
            onItemSelected = onYearChosen
        )
        Spacer(modifier = Modifier.width(10.dp))
        DateItemsPicker(
            isValidDate = isValidDate,
            max = currentMonth,
            items = monthsNumber.toImmutableList(),
            firstIndex = chosenMonth,
            onItemSelected = onMonthChosen
        )
        Spacer(modifier = Modifier.width(10.dp))
        DateItemsPicker(
            isValidDate = isValidDate,
            max = currentDay - 1,
            items = when {
                (chosenYear % 4 == 0) && chosenMonth == 2 -> days29.toImmutableList()
                chosenMonth == 2 -> days28.toImmutableList()
                days30Months.contains(chosenMonth) -> days30.toImmutableList()
                else -> days31.toImmutableList()
            },
            firstIndex = chosenDay - 1,
            onItemSelected = onDayChosen
        )
    }
}

@Composable
fun DateItemsPicker(
    isValidDate: Boolean,
    max: Int,
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(!listState.isScrollInProgress, isValidDate) {
        if (isValidDate) {
            onItemSelected(currentValue.value)
            listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
        } else {
            if (max < currentValue.value.toInt()) {
                listState.animateScrollToItem(index = max)
                onItemSelected(currentValue.value)
            }
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
                color = Black
            )
            HorizontalDivider(
                modifier = Modifier.size(height = 1.dp, width = 60.dp),
                color = Black
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
                    modifier = Modifier.alpha(if (it == firstVisibleItemIndex + 1) 1f else 0.3f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

private val currentYear = Calendar.getInstance().get(Calendar.YEAR)
private val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
private val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

private const val START_YEAR = 1950
private const val END_YEAR = 2100
private val years = (listOf("") + (START_YEAR..END_YEAR).map { it.toString() } + listOf("")).toImmutableList()
private val monthsNumber = (listOf("") + (1..12).map { it.toString() } + listOf("")).toImmutableList()
private val days28 = (listOf("") + (1..28).map { it.toString() } + listOf("")).toImmutableList()
private val days29 = (listOf("") + (1..29).map { it.toString() } + listOf("")).toImmutableList()
private val days30 = (listOf("") + (1..30).map { it.toString() } + listOf("")).toImmutableList() // 4,6,9,11
private val days31 = (listOf("") + (1..31).map { it.toString() } + listOf("")).toImmutableList() // 1,3,5,7,8,10.12
private val days30Months = immutableListOf(4, 6, 9, 11)

//@@Preview(showBackground = true)
//@Composable
//private fun DatePickerPreview() {
//    TerningPointTheme {
//        DatePicker(
//            value = "2024.12.25",
//            onClicked = {},
//            borderColor = getLevelTextColor(2),
//            placeHolder = "날짜를 입력해주세요.",
//            isEditable = true
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//private fun CustomDatePickerPreview() {
//    val chosenYear = remember { mutableIntStateOf(currentYear) }
//    val chosenMonth = remember { mutableIntStateOf(currentMonth) }
//    val chosenDay = remember { mutableIntStateOf(currentDay) }
//    TerningPointTheme {
//        DatePickerUI(
//            isValidDate = true,
//            chosenYear = chosenYear.value,
//            chosenMonth = chosenMonth.value,
//            chosenDay = chosenDay.value,
//            onYearChosen = { chosenYear.value = it },
//            onMonthChosen = { chosenMonth.value = it },
//            onDayChosen = { chosenDay.value = it }
//        )
//    }
//}

@Composable
fun getLevelTextColor(rank: Int) = when (rank) {
    1 -> CalPink
    2 -> CalPurple
    3 -> Color.Green
    else -> Color.Red
}