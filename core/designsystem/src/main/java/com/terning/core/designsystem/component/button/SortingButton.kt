package com.terning.core.designsystem.component.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.R
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.type.SortBy

@Composable
fun SortingButton(
    modifier: Modifier = Modifier,
    sortBy: Int = 0,
    onCLick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Grey350,
                shape = RoundedCornerShape(5.dp)
            )
            .noRippleClickable(onCLick),
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
