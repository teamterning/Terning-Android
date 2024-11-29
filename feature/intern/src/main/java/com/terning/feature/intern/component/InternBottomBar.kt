package com.terning.feature.intern.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.domain.entity.intern.InternInfo
import com.terning.feature.R
import com.terning.feature.intern.InternViewModel

@Composable
fun InternBottomBar(
    modifier: Modifier,
    internInfo: InternInfo,
    onScrapClick: (InternInfo) -> Unit,
    viewModel: InternViewModel = hiltViewModel(),
) {
    val amplitudeTracker = com.terning.core.analytics.LocalTracker.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                color = Grey150,
                shadowWidth = 2.dp,
                offsetY = (-2).dp,
            )
            .background(White)
    ) {
        Column(
            modifier = modifier.padding(
                vertical = 10.dp,
                horizontal = 24.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = modifier.padding(end = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (internInfo.isScrapped) {
                                R.drawable.ic_scrap_true_24
                            } else {
                                R.drawable.ic_scrap_false_24
                            }
                        ),
                        contentDescription = null,
                        modifier = modifier
                            .padding(bottom = 6.dp)
                            .noRippleClickable {
                                onScrapClick(internInfo)
                            },
                        tint = if (internInfo.isScrapped) TerningMain else Grey350
                    )
                    Text(
                        text = stringResource(
                            id = R.string.intern_view_count_detail,
                            internInfo.scrapCount
                        ),
                        style = TerningTheme.typography.detail3,
                        color = Grey400
                    )
                }
                RoundButton(
                    style = TerningTheme.typography.button2,
                    paddingVertical = 16.dp,
                    cornerRadius = 10.dp,
                    text = R.string.intern_move_to_site,
                    onButtonClick = {
                        amplitudeTracker.track(
                            type = EventType.CLICK,
                            name = "detail_url"
                        )
                        viewModel.updateShowWeb(true)
                    },
                )
            }
        }
    }
}
