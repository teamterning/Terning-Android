package com.terning.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.R
import com.terning.core.designsystem.theme.Grey100
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.type.SortBy

@Composable
internal fun HomeSortingButton(
    modifier: Modifier = Modifier,
    sortBy: Int = 0,
    onCLick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) Grey100 else Color.Transparent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onCLick
            )
            .border(
                width = 1.dp,
                color = Grey350,
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                backgroundColor,
                RoundedCornerShape(5.dp)
            ),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_down_18),
            contentDescription = stringResource(id = R.string.sort_button_description),
            tint = Grey350,
            modifier = Modifier
                .padding(
                    start = 7.dp,
                    end = 6.dp,
                    top = 5.dp,
                    bottom = 5.dp,
                )
                .size(20.dp)
        )
        Text(
            text = stringResource(
                id = SortBy.entries[sortBy].sortBy
            ),
            style = TerningTheme.typography.button3,
            color = Grey350,
            modifier = Modifier
                .padding(end = 11.dp)
        )
    }
}
