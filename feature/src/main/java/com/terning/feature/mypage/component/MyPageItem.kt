package com.terning.feature.mypage.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.feature.R

@Composable
fun MyPageItem(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        TerningImage(
            painter = R.drawable.ic_my_page,
            modifier = modifier.padding(start = 12.dp)
        )
        Text(text = text)
        TerningImage(painter = R.drawable.ic_my_page_go)
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageItemPreview() {
    TerningPointTheme {
        MyPageItem(text = "공지사항")
    }
}
