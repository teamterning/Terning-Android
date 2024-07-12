package com.terning.feature.calendar.calendar.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme

@Composable
fun ScrapCancelDialog(

) {

}

@Composable
private fun ScrapCancelDialogScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(width = 260.dp, height = 203.dp),
            content = {}
        )

        Text(
            text = "관심 공고가 캘린더에서 사라져요!",
            color = Grey500,
            style = TerningTheme.typography.title4,
            modifier = Modifier.padding(top = 21.dp)
        )
        Text(
            text = "스크랩을 취소하시겠어요?",
            color = Grey350,
            style = TerningTheme.typography.body5,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScrapCancelDialogPreview() {
    TerningPointTheme {
        Box(
            modifier = Modifier.size(
                width = 300.dp,
                height = 421.dp
            )
        ) {
            ScrapCancelDialogScreen(
                modifier = Modifier
            )
        }

    }
}