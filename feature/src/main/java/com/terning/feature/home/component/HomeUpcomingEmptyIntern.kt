package com.terning.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R

@Composable
fun HomeUpcomingEmptyIntern(
    modifier: Modifier = Modifier,
    navigateToCalendar: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .customShadow(
                color = Grey150,
                shadowRadius = 5.dp,
                shadowWidth = 1.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.home_upcoming_empty),
                modifier = modifier
                    .padding(top = 26.dp),
                textAlign = TextAlign.Center,
                style = TerningTheme.typography.detail2,
                color = Grey500,
            )
            Card(
                colors = CardDefaults.cardColors(White),
                modifier = modifier
                    .padding(top = 8.dp, bottom = 25.dp)
                    .border(
                        width = 1.dp,
                        color = TerningMain,
                        shape = RoundedCornerShape(14.dp),
                    )
                    .noRippleClickable(navigateToCalendar),
            ) {
                Text(
                    text = stringResource(id = R.string.home_upcoming_check_schedule),
                    style = TerningTheme.typography.button4,
                    color = TerningMain,
                    modifier = modifier
                        .padding(vertical = 8.dp, horizontal = 10.dp)
                )
            }
        }
    }
}