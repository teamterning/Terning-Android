package com.terning.feature.filtering.filteringtwo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.filtering.filteringtwo.component.StatusTwoRadioGroup

@Composable
fun FilteringTwoRoute(
    grade: Int,
    onNextClick: (Int, Int) -> Unit,
    navigateUp: () -> Unit,
    viewModel: FilteringTwoViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is FilteringTwoSideEffect.NavigateUp -> navigateUp()
                }
            }
    }

    FilteringTwoScreen(
        grade = grade,
        onNextClick = onNextClick,
        navigateUp = viewModel::navigateUp,
        onButtonClick = { index ->
            viewModel.updateWorkingPeriod(index)
            viewModel.updateButtonValidation()
        },
        buttonState = state.isButtonValid,
        workingPeriod = state.workingPeriod
    )
}

@Composable
fun FilteringTwoScreen(
    grade: Int,
    onNextClick: (Int, Int) -> Unit,
    navigateUp: () -> Unit,
    onButtonClick: (Int) -> Unit,
    buttonState: Boolean,
    workingPeriod: Int
) {
    Column(
        modifier = Modifier
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = { navigateUp() }
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_filtering_status2),
                modifier = Modifier.padding(
                    top = 28.dp,
                    start = 24.dp
                ),
                contentDescription = "filtering two status"
            )
            Text(
                text = stringResource(id = R.string.filtering_status2_title),
                style = TerningTheme.typography.title3,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(id = R.string.filtering_status2_sub),
                style = TerningTheme.typography.body5,
                color = Grey300,
                modifier = Modifier.padding(
                    top = 4.dp,
                    start = 24.dp,
                    bottom = 24.dp
                )
            )
            StatusTwoRadioGroup(
                onButtonClick = { index ->
                    onButtonClick(index)
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 20.dp,
                text = R.string.filtering_button,
                onButtonClick = { onNextClick(grade, workingPeriod) },
                modifier = Modifier.padding(bottom = 12.dp),
                isEnabled = buttonState
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilteringTwoScreenPreview() {
    TerningPointTheme {
        FilteringTwoScreen(
            grade = 1,
            onNextClick = { _, _ -> },
            navigateUp = { },
            onButtonClick = { },
            buttonState = true,
            workingPeriod = 1
        )
    }
}