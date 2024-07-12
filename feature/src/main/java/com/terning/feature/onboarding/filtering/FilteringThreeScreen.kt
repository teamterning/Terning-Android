package com.terning.feature.onboarding.filtering

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.terning.core.designsystem.component.button.RectangleButton
import com.terning.core.designsystem.component.datepicker.DatePickerUI
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.component.topappbar.BackButtonTopAppBar
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.onboarding.filtering.navigation.navigateFilteringThree
import com.terning.feature.onboarding.starthome.navigation.navigateStartHome
import java.util.Calendar

@Composable
fun FilteringThreeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    filteringViewModel: FilteringViewModel = hiltViewModel(),
    onButtonClick: () -> Unit = {},
) {
    var chosenYear by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    var chosenMonth by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.MONTH)) }

    val isDateMove by remember { mutableStateOf(false) }

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
                modifier = modifier.padding(
                    top = 3.dp,
                    start = 24.dp,
                    bottom = 25.dp
                )
            )
            DatePickerUI(
                chosenYear = chosenYear,
                chosenMonth = chosenMonth,
                onYearChosen = { chosenYear = it },
                onMonthChosen = { chosenMonth = it },
            )
            Spacer(modifier = modifier.weight(1f))
            RectangleButton(
                style = TerningTheme.typography.button0,
                isEnabled = isDateMove,
                paddingVertical = 25.dp,
                text = R.string.filtering_button,
                onButtonClick = { navController.navigateStartHome() },
                modifier = modifier.padding(bottom = 12.dp),
            )
        }
    }
}