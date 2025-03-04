package com.terning.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.extension.customShadow
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningSub1
import com.terning.core.designsystem.theme.TerningSub5
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.home.R

@Composable
fun HomeUpcomingEmptyIntern(
    modifier: Modifier = Modifier,
    navigateToCalendar: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

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

            Text(
                text = stringResource(id = R.string.home_upcoming_check_schedule),
                style = TerningTheme.typography.button4,
                color = TerningMain,
                modifier = modifier
                    .padding(top = 8.dp, bottom = 25.dp)
                    .border(
                        width = 1.dp,
                        color = if (isPressed) TerningSub1 else TerningMain,
                        shape = CircleShape,
                    )
                    .background(
                        color = if (isPressed) TerningSub5 else Color.Transparent,
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = navigateToCalendar
                    )
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            )
        }
    }
}