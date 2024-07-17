package com.terning.feature.home.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.terning.domain.entity.response.HomeTodayInternModel

@Composable
fun HomeTodayIntern(internList: List<HomeTodayInternModel>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(internList.size) { index ->
            HomeTodayInternItem(
                title = internList[index].title,
                scrapColor = Color(android.graphics.Color.parseColor(internList[index].color))
            )
        }
    }
}
