package com.terning.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.feature.home.TerningPostItem

@Composable
fun HomeRecommendIntern(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(10) {
            TerningPostItem(
                imageUrl = "https://reqres.in/img/faces/7-image.jpg",
                title = "[Someone] 콘텐츠 마케터 대학생 인턴 채용",
                dateDeadline = "2",
                workingPeriod = "2개월",
                isScraped = false,
            )
        }
    }
}