package com.terning.feature.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White
import com.terning.feature.R

@Composable
fun SearchIntern() {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            10.dp,
            Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                // TODO 효빈 그림자 PR 풀 받아서 바꾸기
                spotColor = Color(0x26DDDDDD),
                ambientColor = Color(0x26DDDDDD)
            )
            .background(
                color = White,
                shape = RoundedCornerShape(size = 5.dp)
            )
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_nav_search),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Grey400)
        )
        Text(
            text = "[유한킴벌리]\n그린캠프 w. 대학생 숲 \n활동가 모집",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = TerningTheme.typography.body6,
            color = Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
    }
}