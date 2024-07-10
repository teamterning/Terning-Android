package com.terning.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.R

@Composable
fun HomeTodayEmptyIntern(
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