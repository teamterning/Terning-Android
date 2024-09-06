package com.terning.feature.filtering.filtering

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.R
import com.terning.feature.filtering.filtering.component.StatusTwoRadioGroup

@Composable
fun FilteringTwoScreen(
    grade: Int,
    onNextClick: (Int, Int) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    onButtonClick: (Int) -> Unit = {},
) {
    val isButtonValid = remember { mutableStateOf(false) }

    var workingPeriod by remember { mutableIntStateOf(-1) }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .background(White)
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = { navigateUp() }
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TerningImage(
                painter = R.drawable.ic_filtering_status2,
                modifier = modifier.padding(
                    top = 20.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(id = R.string.filtering_status2_title),
                style = TerningTheme.typography.title3,
                modifier = modifier.padding(
                    top = 19.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(id = R.string.filtering_status2_sub),
                style = TerningTheme.typography.body5,
                color = Grey300,
                modifier = modifier.padding(
                    top = 3.dp,
                    start = 24.dp,
                    bottom = 25.dp
                )
            )
            StatusTwoRadioGroup(
                onButtonClick = { index ->
                    onButtonClick(index)
                    isButtonValid.value = true
                    workingPeriod = index
                }
            )
            Spacer(modifier = modifier.weight(1f))
            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 20.dp,
                text = R.string.filtering_button,
                onButtonClick = { onNextClick(grade, workingPeriod) },
                modifier = modifier.padding(bottom = 12.dp),
                isEnabled = isButtonValid.value
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
            navigateUp = { }
        )
    }
}