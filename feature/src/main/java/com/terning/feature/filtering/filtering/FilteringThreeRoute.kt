package com.terning.feature.filtering.filtering

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.datepicker.DatePickerUI
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.toast
import com.terning.feature.R
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

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

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
                    is FilteringSideEffect.NavigateToStartHome -> navigateToStartHome()
                    is FilteringSideEffect.ShowToast -> context.toast(sideEffect.message)
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
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    chosenYear: Int,
    chosenMonth: Int,
    onYearChosen: (Int) -> Unit,
    onMonthChosen: (Int) -> Unit,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = { navigateUp() }
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TerningImage(
                painter = R.drawable.ic_filtering_status3,
                modifier = modifier.padding(
                    top = 20.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(id = R.string.filtering_status3_title),
                style = TerningTheme.typography.title3,
                modifier = modifier.padding(
                    top = 19.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(id = R.string.filtering_status3_sub),
                style = TerningTheme.typography.body5,
                color = Grey300,
                modifier = modifier.padding(
                    top = 3.dp,
                    start = 24.dp,
                    bottom = 25.dp
                )
            )
            Spacer(modifier = modifier.weight(1f))
            DatePickerUI(
                chosenYear = chosenYear,
                chosenMonth = chosenMonth,
                onYearChosen = { onYearChosen(it) },
                onMonthChosen = { onMonthChosen(it) },
            )
            Spacer(modifier = modifier.weight(3f))
            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 20.dp,
                text = R.string.filtering_button,
                onButtonClick = { onNextClick() },
                modifier = modifier.padding(bottom = 12.dp),
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