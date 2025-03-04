package com.terning.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.analytics.EventType
import com.terning.core.analytics.LocalTracker
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningSub5
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.home.R

@Composable
fun HomeFilteringScreen(
    onChangeFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val amplitudeTracker = LocalTracker.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = TerningMain,
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                color = if (isPressed) TerningSub5 else Color.Transparent,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    amplitudeTracker.track(
                        type = EventType.CLICK,
                        name = "filtering"
                    )
                    onChangeFilterClick()
                }
            )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home_filtering_28),
            contentDescription = stringResource(id = R.string.home_recommend_filtering),
            modifier = Modifier
                .padding(
                    start = 2.dp,
                    top = 1.dp,
                    bottom = 1.dp,
                ),
            tint = TerningMain,
        )
        Text(
            text = stringResource(id = R.string.home_recommend_filtering),
            style = TerningTheme.typography.button3,
            color = TerningMain,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 5.dp, end = 11.dp)
                .align(Alignment.CenterVertically),
        )
    }
}
