package com.terning.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.R

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

        Box(
            modifier = Modifier
                .background(TerningMain)
                .padding(vertical = 12.dp)
                .width(148.dp)
                .height(36.dp)
        )

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
        HomeNoClosedTodayItem(isButtonExist = false)

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
    }
}

@Composable
fun HomeNoClosedTodayItem(
    isButtonExist: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 19.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        border = BorderStroke(
            1.dp,
            Grey150,
        ),
        colors = CardDefaults.cardColors(White),
    ) {
        if (isButtonExist) {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.home_today_no_closed),
                    modifier = modifier
                        .padding(top = 27.dp),
                    textAlign = TextAlign.Center,
                    style = TerningTheme.typography.detail3,
                    color = Grey500,
                )
                Card(
                    colors = CardDefaults.cardColors(White),
                    modifier = modifier
                        .padding(top = 7.dp, bottom = 27.dp)
                        .border(
                            width = 1.dp,
                            color = Grey400,
                            shape = RoundedCornerShape(12.dp),
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.home_today_check_schedule),
                        style = TerningTheme.typography.button4,
                        color = Grey400,
                        modifier = modifier
                            .padding(vertical = 8.dp, horizontal = 10.dp)
                    )
                }
            }
        } else {
            Text(
                text = stringResource(id = R.string.home_today_no_scrap),
                modifier = modifier
                    .padding(vertical = 44.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = TerningTheme.typography.detail2,
                color = Grey500,
            )
        }
    }
}

@Composable
private fun HomeClosingTodayPost() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 19.dp),
    ) {
        items(5) {
            HomeClosingTodayItem("[유한킴벌리] 그린캠프 w.대학생 숲활동가 모집")
        }
    }
}