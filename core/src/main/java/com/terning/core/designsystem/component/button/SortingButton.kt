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
import com.terning.core.designsystem.component.bottomsheet.SortBy
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable

@Composable
fun SortingButton(
    modifier: Modifier = Modifier,
    sortBy: Int = 0,
    onCLick: () -> Unit,
) {
    Row(
        modifier = modifier
            .noRippleClickable { onCLick() },
    ) {
        Text(
            text = stringResource(
                id = SortBy.entries[sortBy].type
            ),
            style = TerningTheme.typography.button3,
            color = Black,
            modifier = Modifier
                .padding(
                    top = 6.dp,
                    bottom = 6.dp,
                )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_down_18),
            contentDescription = stringResource(id = R.string.sort_button_description),
            modifier = Modifier
                .padding(
                    start = 2.dp,
                    end = 2.dp,
                    top = 6.dp,
                    bottom = 4.dp,
                )
        )
    }
}
