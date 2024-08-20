package com.terning.feature.filtering.filtering

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.filtering.filtering.component.StatusOneRadioGroup
import com.terning.feature.filtering.filtering.navigation.navigateFilteringTwo
import com.terning.feature.main.MainNavigator
import com.terning.feature.main.rememberMainNavigator

@Composable
fun FilteringOneScreen(
    name: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    onButtonClick: (Int) -> Unit = {},
) {
    val isButtonValid = remember { mutableStateOf(false) }

    var grade by remember { mutableIntStateOf(-1) }

    Column(
        modifier = modifier
    ) {
        BackButtonTopAppBar(
            onBackButtonClick = { navController.navigateUp() }
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TerningImage(
                painter = R.drawable.ic_filtering_status1,
                modifier = modifier.padding(
                    top = 20.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(
                    id = R.string.filtering_status1_title,
                    name
                ),
                style = TerningTheme.typography.title3,
                modifier = modifier.padding(
                    top = 19.dp,
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
                modifier = modifier.padding(
                    top = 3.dp,
                    start = 24.dp,
                    bottom = 25.dp
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
                modifier = modifier.padding(
                    start = 24.dp,
                    top = 9.dp
                )
            )
            Spacer(modifier = modifier.weight(1f))
            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 20.dp,
                text = R.string.filtering_button,
                onButtonClick = { navController.navigateFilteringTwo(grade) },
                modifier = modifier.padding(bottom = 12.dp),
                isEnabled = isButtonValid.value
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilteringOneScreenPreview() {
    val navigator: MainNavigator = rememberMainNavigator()
    TerningPointTheme {
        FilteringOneScreen(
            name = "터닝이",
            navController = navigator.navController,
            onButtonClick = {}
        )
    }
}