package com.terning.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White

@Composable
fun HomeTodayInternItem(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .background(White)
            .width(140.dp)
            .height(116.dp)
            .border(
                width = 1.dp,
                color = Grey150,
                RoundedCornerShape(5.dp),
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            modifier
                .background(
                    color = Grey500,
                    shape = RoundedCornerShape(
                        topStart = 5.dp,
                        bottomStart = 5.dp
                    )
                )
                .width(8.dp)
                .fillMaxHeight()
        )
        Text(
            text = title,
            modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            style = TerningTheme.typography.button3
        )
    }
}