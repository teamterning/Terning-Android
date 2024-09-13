package com.terning.feature.filtering.filteringone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.terning.core.designsystem.theme.White
import com.terning.feature.R
import com.terning.feature.filtering.filteringone.component.StatusOneRadioGroup

@Composable
fun FilteringOneRoute(
    name: String,
    onNextClick: (String) -> Unit,
    navigateUp: () -> Unit,
    paddingValues: PaddingValues,
    viewModel: FilteringOneViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = true) {
        viewModel.updateButton(false)
    }

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is FilteringOneSideEffect.NavigateUp -> navigateUp()
                    is FilteringOneSideEffect.NavigateToFilteringTwo -> onNextClick(sideEffect.grade)
                }
            }
    }

    FilteringOneScreen(
        name = name,
        onButtonClick = { grade ->
            viewModel.updateGrade(grade)
            viewModel.updateButton(true)
        },
        onNextClick = viewModel::navigateToFilteringTwo,
        navigateUp = viewModel::navigateUp,
        buttonState = state.isButtonValid,
        gradeState = state.grade,
        paddingValues = paddingValues
    )
}

@Composable
fun FilteringOneScreen(
    name: String,
    onNextClick: (String) -> Unit,
    navigateUp: () -> Unit,
    onButtonClick: (String) -> Unit,
    buttonState: Boolean,
    gradeState: String,
    paddingValues: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(White)
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = navigateUp
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_filtering_status1),
                modifier = Modifier.padding(
                    top = 28.dp,
                    start = 24.dp
                ),
                contentDescription = "filtering one status"
            )
            Text(
                text = stringResource(
                    id = R.string.filtering_status1_title,
                    name
                ),
                style = TerningTheme.typography.title3,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(
                    id = R.string.filtering_status1_sub,
                    name
                ),
                style = TerningTheme.typography.body5,
                color = Grey300,
                modifier = Modifier.padding(
                    top = 4.dp,
                    start = 24.dp,
                    bottom = 24.dp
                )
            )
            StatusOneRadioGroup(
                onButtonClick = { grade ->
                    onButtonClick(grade)
                }
            )
            Text(
                text = stringResource(id = R.string.filtering_status1_warning),
                style = TerningTheme.typography.detail3,
                modifier = Modifier.padding(
                    start = 24.dp,
                    top = 8.dp
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 20.dp,
                text = R.string.filtering_button,
                onButtonClick = { onNextClick(gradeState) },
                modifier = Modifier.padding(bottom = 12.dp),
                isEnabled = buttonState
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilteringOneScreenPreview() {
    TerningPointTheme {
        FilteringOneScreen(
            name = "터닝이",
            onButtonClick = {},
            onNextClick = {},
            navigateUp = {},
            buttonState = true,
            gradeState = "freshman"
        )
    }
}