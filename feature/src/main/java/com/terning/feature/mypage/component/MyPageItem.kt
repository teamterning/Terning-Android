package com.terning.feature.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R

@Composable
fun MyPageItem(
    modifier: Modifier = Modifier,
    text: String,
    version: String = ""
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TerningImage(
                painter = R.drawable.ic_my_page,
                modifier = modifier.padding(start = 16.dp)
            )
            Text(
                text = text,
                modifier = modifier.padding(start = 12.dp),
                style = TerningTheme.typography.body5
            )
        }
        if (version.isNotEmpty())
            Text(
                text = version,
                modifier = modifier.padding(end = 16.dp),
                style = TerningTheme.typography.button4
            )
        else TerningImage(
            painter = R.drawable.ic_my_page_go,
            modifier = modifier.padding(end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageItemPreview() {
    TerningPointTheme {
        MyPageItem(text = "공지사항")
    }
}
