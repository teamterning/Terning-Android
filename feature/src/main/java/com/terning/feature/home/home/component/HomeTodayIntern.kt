package com.terning.feature.home.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.feature.home.home.model.ScrapData

@Composable
fun HomeTodayIntern(internList: List<ScrapData>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(internList.size) { index ->
            HomeTodayInternItem(
                title = internList[index].internTitle,
                scrapColor = internList[index].scrapColor,
            )
        }
    }
}
