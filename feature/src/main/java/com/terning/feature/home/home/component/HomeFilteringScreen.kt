package com.terning.feature.home.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable
import com.terning.core.type.Grade
import com.terning.core.type.WorkingPeriod
import com.terning.domain.entity.home.HomeFilteringInfo
import com.terning.feature.R

@Composable
fun HomeFilteringScreen(
    homeFilteringInfo: HomeFilteringInfo,
    onChangeFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = TerningMain,
                    shape = RoundedCornerShape(5.dp)
                )
                .align(Alignment.CenterVertically)
                .noRippleClickable(onChangeFilterClick),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home_filtering_28),
                contentDescription = stringResource(id = R.string.home_recommend_filtering),
                modifier = modifier
                    .padding(start = 1.dp),
                tint = TerningMain,
            )
            Text(
                text = stringResource(id = R.string.home_recommend_filtering),
                style = TerningTheme.typography.button3,
                color = TerningMain,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 5.dp, end = 10.dp)
                    .align(Alignment.CenterVertically),
            )
        }
        with(homeFilteringInfo) {
            if (grade != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HomeFilteringInfoText(
                        text = stringResource(id = Grade.entries[grade ?: 0].text),
                        modifier = Modifier
                            .padding(end = 6.dp),
                    )
                    HomeFilteringInfoDivider()
                    HomeFilteringInfoText(
                        text = stringResource(WorkingPeriod.entries[workingPeriod ?: 0].text),
                        modifier = Modifier
                            .padding(horizontal = 6.dp),
                    )
                    HomeFilteringInfoDivider()
                    HomeFilteringInfoText(
                        text = "$startYear 년 $startMonth 월",
                        modifier = Modifier
                            .padding(start = 6.dp),
                    )
                }
            } else {
                Text(
                    text = "설정된 필터링 정보가 없어요",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                )
            }
        }
    }
}

@Composable
private fun HomeFilteringInfoText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TerningTheme.typography.body5,
        color = Grey400,
        modifier = modifier,
    )
}

@Composable
private fun HomeFilteringInfoDivider() {
    Box(
        modifier = Modifier
            .size(4.dp)
            .clip(CircleShape)
            .background(Grey350),
    )
}