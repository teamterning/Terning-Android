package com.terning.core.designsystem.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun SortingButton(
    sortBy: Int = 0,
    modifier: Modifier = Modifier,
) {
    Row {
        Text(
            text = stringResource(
                id = when (sortBy) {
                    1 -> R.string.sort_by_shortest
                    2 -> R.string.sort_by_longest
                    3 -> R.string.sort_by_scrap
                    4 -> R.string.sort_by_view_count
                    else -> R.string.sort_by_earliest
                }
            ),
            style = TerningTheme.typography.button3,
            color = Black,
            modifier = modifier
                .padding(
                    top = 6.dp,
                    bottom = 5.dp,
                    start = 12.dp,
                )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_down),
            contentDescription = stringResource(id = R.string.sort_button_description),
            modifier = modifier
                .padding(vertical = 5.dp)
                .padding(end = 2.dp)
        )
    }
}
