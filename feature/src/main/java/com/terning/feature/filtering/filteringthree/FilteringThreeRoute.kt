package com.terning.feature.filtering.filteringthree

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.currentMonth
import com.terning.core.extension.currentYear
import com.terning.core.extension.toast
import com.terning.feature.R
import com.terning.feature.filtering.filteringthree.component.YearMonthPicker
import java.util.Calendar

@Composable
fun FilteringThreeRoute(
    grade: Int,
    workingPeriod: Int,
    navigateUp: () -> Unit,
    navigateToStartHome: () -> Unit,
    viewModel: FilteringViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val currentYear = Calendar.getInstance().currentYear
    val currentMonth = Calendar.getInstance().currentMonth

    var chosenYear by remember { mutableIntStateOf(currentYear) }
    var chosenMonth by remember { mutableIntStateOf(currentMonth) }

    LaunchedEffect(key1 = true) {
        viewModel.fetchGrade(grade = grade)
        viewModel.fetchWorkingPeriod(workingPeriod = workingPeriod)
    }

    LaunchedEffect(key1 = chosenYear, key2 = chosenMonth) {
        viewModel.fetchStartYear(chosenYear)
        viewModel.fetchStartMonth(chosenMonth)
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is FilteringThreeSideEffect.NavigateToStartHome -> navigateToStartHome()
                    is FilteringThreeSideEffect.ShowToast -> context.toast(sideEffect.message)
                }
            }
    }

    FilteringThreeScreen(
        navigateUp = { navigateUp() },
        chosenYear = chosenYear,
        chosenMonth = chosenMonth,
        onNextClick = { viewModel.postFilteringWithServer() },
        onYearChosen = { chosenYear = it },
        onMonthChosen = { chosenMonth = it }
    )
}

@Composable
fun FilteringThreeScreen(
    navigateUp: () -> Unit,
    chosenYear: Int,
    chosenMonth: Int,
    onYearChosen: (Int) -> Unit,
    onMonthChosen: (Int) -> Unit,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = Modifier,
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = { navigateUp() }
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_filtering_status3),
                modifier = Modifier.padding(
                    top = 28.dp,
                    start = 24.dp
                ),
                contentDescription = "filtering three status"
            )
            Text(
                text = stringResource(id = R.string.filtering_status3_title),
                style = TerningTheme.typography.title3,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(id = R.string.filtering_status3_sub),
                style = TerningTheme.typography.body5,
                color = Grey300,
                modifier = Modifier.padding(
                    top = 4.dp,
                    start = 24.dp,
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            YearMonthPicker(
                chosenYear = chosenYear,
                chosenMonth = chosenMonth,
                onYearChosen = { onYearChosen(it) },
                onMonthChosen = { onMonthChosen(it) },
            )
            Spacer(modifier = Modifier.weight(7f))
            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 20.dp,
                text = R.string.filtering_button,
                onButtonClick = onNextClick,
                modifier = Modifier.padding(bottom = 12.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilteringThreeScreenPreview() {
    TerningPointTheme {
        FilteringThreeScreen(
            navigateUp = { },
            chosenYear = 2024,
            chosenMonth = 8,
            onYearChosen = {},
            onMonthChosen = {},
            onNextClick = {}
        )
    }
}