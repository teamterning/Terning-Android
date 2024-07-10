package com.terning.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.home.component.HomeFilteringScreen
import com.terning.feature.home.component.HomeRecommendEmptyIntern
import com.terning.feature.home.component.HomeTodayIntern

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.home_today_title_start
            )
                    + "남지우"
                    + stringResource(
                id = R.string.home_today_title_end
            ),
            modifier = Modifier
                .padding(top = 11.dp),
            style = TerningTheme.typography.title1,
            color = Black,
        )

        HomeTodayIntern()
//        HomeNoClosedTodayItem(isButtonExist = false)

        Text(
            text = stringResource(id = R.string.home_recommend_sub_title),
            style = TerningTheme.typography.detail2,
            color = Black,
            modifier = Modifier
                .padding(top = 25.dp)
        )

        Text(
            text = stringResource(id = R.string.home_recommend_main_title),
            style = TerningTheme.typography.title1,
            color = Black,
            modifier = Modifier
                .padding(top = 5.dp)
        )

        HomeFilteringScreen(3, 1, 7)

        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .height(4.dp)
        )

//        HomeRecommendIntern()
        HomeRecommendEmptyIntern()
    }
}

