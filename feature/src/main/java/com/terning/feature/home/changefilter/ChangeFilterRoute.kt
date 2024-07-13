package com.terning.feature.home.changefilter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.datepicker.DatePickerUI
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.home.changefilter.component.FilteringMainTitleText
import com.terning.feature.home.changefilter.component.FilteringSubTitleText
import com.terning.feature.home.home.navigation.navigateHome

@Composable
fun ChangeFilterRoute(
    navController: NavController,
) {
    ChangeFilterScreen(navController)
}

@Composable
fun ChangeFilterScreen(navController: NavController) {
    Scaffold(
        topBar = {
            BackButtonTopAppBar(
                title = stringResource(id = R.string.change_filter_top_bar_title),
                onBackButtonClick = { navController.popBackStack() },
                modifier = Modifier
                    .shadow(elevation = 2.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                )
        ) {
            showTitle(
                mainTitle = stringResource(id = R.string.change_filter_grade_main),
                subTitle = stringResource(id = R.string.filtering_status1_sub),
                modifier = Modifier.padding(
                    top = 31.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            )

            showTitle(
                mainTitle = stringResource(id = R.string.filtering_status2_title),
                subTitle = stringResource(id = R.string.filtering_status2_sub),
                modifier = Modifier.padding(
                    top = 39.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            )

            showTitle(
                mainTitle = stringResource(id = R.string.filtering_status3_title),
                subTitle = stringResource(id = R.string.filtering_status3_sub),
                modifier = Modifier.padding(
                    top = 39.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            )

            Spacer(modifier = Modifier.padding(12.dp))
            Spacer(modifier = Modifier.weight(1f))
            DatePickerUI(
                chosenYear = 2024,
                chosenMonth = 7,
            )
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(33.dp))

            RectangleButton(
                style = TerningTheme.typography.button0,
                paddingVertical = 19.dp,
                text = R.string.change_filter_save,
                onButtonClick = {
                    navController.navigateHome()
                }
            )
        }

    }
}

@Composable
private fun showTitle(
    mainTitle: String,
    subTitle: String,
    modifier: Modifier = Modifier,
) {
    FilteringMainTitleText(
        text = mainTitle,
        modifier = modifier.padding()
    )
    FilteringSubTitleText(
        text = subTitle,
        modifier = Modifier
            .padding(
                top = 3.dp,
                bottom = 13.dp,
                start = 24.dp,
                end = 24.dp
            )
    )
}