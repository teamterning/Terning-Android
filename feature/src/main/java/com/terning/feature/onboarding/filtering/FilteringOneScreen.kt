package com.terning.feature.onboarding.filtering

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.onboarding.filtering.component.StatusOneRadioGroup

@Composable
fun FilteringOneScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    filteringViewModel: FilteringViewModel = hiltViewModel(),
    onButtonClick: () -> Unit = {},
    onNextButton: () -> Unit = {}
) {
    val name = "남지우"
    Scaffold(
        modifier = modifier,
        topBar = {
            BackButtonTopAppBar(
                onBackButtonClick = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TerningImage(
                painter = R.drawable.ic_filtering_status1,
                modifier = modifier.padding(
                    top = 20.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(id = R.string.filtering_status1_title, name),
                style = TerningTheme.typography.title3,
                modifier = modifier.padding(
                    top = 19.dp,
                    start = 24.dp
                )
            )
            Text(
                text = stringResource(id = R.string.filtering_status1_sub, name),
                style = TerningTheme.typography.body5,
                modifier = modifier.padding(
                    top = 3.dp,
                    start = 24.dp
                )
            )
            StatusOneRadioGroup(
                onButtonClick = {
                    onButtonClick()
                }
            )
            Text(
                text = stringResource(id = R.string.filtering_status1_warning),
                style = TerningTheme.typography.detail3
            )
            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 20.dp,
                text = R.string.filtering_button,
                onButtonClick = { onNextButton() })
        }
    }
}
