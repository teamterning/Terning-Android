package com.terning.feature.filtering.filtering

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.filtering.filtering.component.StatusOneRadioGroup

@Composable
fun FilteringOneScreen(
    name: String,
    onNextClick: (Int) -> Unit,
    navigateUp: () -> Unit,
    onButtonClick: (Int) -> Unit = {},
) {
    val isButtonValid = remember { mutableStateOf(false) }
    var grade by remember { mutableIntStateOf(-1) }

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
                onButtonClick = { index ->
                    onButtonClick(index)
                    isButtonValid.value = true
                    grade = index
                }
            )
            Text(
                text = stringResource(id = R.string.filtering_status1_warning),
                style = TerningTheme.typography.detail3,
                modifier = Modifier.padding(
                    start = 24.dp,
                    top = 9.dp
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 20.dp,
                text = R.string.filtering_button,
                onButtonClick = { onNextClick(grade) },
                modifier = Modifier.padding(bottom = 12.dp),
                isEnabled = isButtonValid.value
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
            navigateUp = {}
        )
    }
}