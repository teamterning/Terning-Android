package com.terning.feature.home.component.bottomsheet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.type.JobType

@Composable
fun JobFilteringScreen(
    initOption: Int,
    onButtonClick: (JobType) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember { mutableIntStateOf(initOption) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 60.dp, start = 23.dp, end = 23.dp),

        ) {
        itemsIndexed(JobType.entries) { index, option ->
            JobTypeItem(
                jobType = option,
                onButtonClick = {
                    selectedIndex = index
                    onButtonClick(option)
                },
                isSelected = selectedIndex == index
            )
        }
    }
}

@Composable
private fun JobTypeItem(
    jobType: JobType,
    isSelected: Boolean,
    onButtonClick: (JobType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (isSelected) TerningMain else Grey200,
                shape = RoundedCornerShape(10.dp)
            )
            .noRippleClickable { onButtonClick(jobType) }
            .padding(
                top = 31.dp,
                bottom = 33.dp,
                start = 27.dp,
                end = 26.dp
            ),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = jobType.drawableResId),
            contentDescription = jobType.stringValue,
            tint = if (isSelected) TerningMain else Grey300
        )

        Text(
            text = stringResource(id = jobType.stringResId),
            style = TerningTheme.typography.body6,
            color = if (isSelected) TerningMain else Grey350,
            modifier = Modifier
                .padding(top = 7.dp)
        )
    }
}