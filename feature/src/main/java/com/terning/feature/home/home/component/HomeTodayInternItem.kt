package com.terning.feature.home.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.item.ScrapBox
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun HomeTodayInternItem(
    title: String,
    scrapColor: Color,
    modifier: Modifier = Modifier
) {
    ScrapBox(
        modifier = modifier
            .height(116.dp)
            .width(140.dp),
        cornerRadius = 5.dp,
        scrapColor = scrapColor,
        borderWidth = 1.dp,
        borderColor = Grey150,
        content = {
            Column(
                modifier = modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = title,
                    modifier = modifier
                        .padding(start = 8.dp, end = 9.dp, bottom = 8.dp),
                    style = TerningTheme.typography.button3,
                    color = Black,
                    maxLines = 3,
                )
            }
        }
    )
}