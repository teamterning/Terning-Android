package com.terning.feature.intern.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R

@Composable
fun InternBottomBar(
    modifier: Modifier,
) {
    var isScrap by remember { mutableStateOf(false) }
    var viewCount by remember { mutableIntStateOf(512) }

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
            ) {
                Column(
                    modifier = modifier.padding(end = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        painter = painterResource(
                            id =
                            if (isScrap) R.drawable.ic_scrap_true_24
                            else R.drawable.ic_scrap_false_24,
                        ),
                        contentDescription = null,
                        modifier = modifier
                            .padding(
                                bottom = 6.dp
                            )
                            .noRippleClickable {
                                isScrap = !isScrap
                                if (isScrap) viewCount += 1 else viewCount -= 1
                            },
                        tint = if (isScrap) TerningMain else Grey350
                    )
                    Text(
                        text = stringResource(
                            id = R.string.intern_view_count_detail,
                            viewCount
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
                    onButtonClick = { /*TODO*/ })
            }
        }
    }
}