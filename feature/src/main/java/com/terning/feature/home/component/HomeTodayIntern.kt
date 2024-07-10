package com.terning.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeTodayIntern() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 19.dp),
    ) {
        items(5) {
            HomeTodayInternItem("[유한킴벌리] 그린캠프 w.대학생 숲활동가 모집")
        }
    }
}
