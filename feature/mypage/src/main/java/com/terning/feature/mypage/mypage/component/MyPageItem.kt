package com.terning.feature.mypage.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.component.image.TerningImage
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.mypage.R

@Composable
internal fun MyPageItem(
    text: String,
    icon: Int,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
    trailingContent: @Composable () -> Unit = { TerningImage(painter = R.drawable.ic_my_page_go_detail) }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable {
                onButtonClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "my page image",
                modifier = modifier.size(28.dp)
            )
            Text(
                text = text,
                modifier = modifier.padding(start = 12.dp),
                style = TerningTheme.typography.body5
            )
        }
        trailingContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageItemPreview() {
    TerningPointTheme {
        MyPageItem(
            text = "공지사항",
            icon = R.drawable.ic_my_page_notice
        )
    }
}
