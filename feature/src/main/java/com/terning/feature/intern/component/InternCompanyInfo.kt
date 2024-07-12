package com.terning.feature.intern.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R

@Composable
fun InternCompanyInfo(modifier: Modifier) {
    Row(
        modifier = modifier.padding(
            start = 20.dp,
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    2.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .size(60.dp)
                        .border(
                            width = 1.dp,
                            color = TerningMain,
                            shape = RoundedCornerShape(size = 30.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_nosearch
                        ),
                        modifier = modifier.fillMaxWidth(),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    3.dp,
                    Alignment.Bottom
                ),
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .padding(
                        horizontal = 12.dp
                    )
            ) {
                Text(
                    text = "모니모니",
                    style = TerningTheme.typography.title4,
                    color = Black,
                    modifier = modifier.padding(top = 11.dp)
                )
                Text(
                    text = "스타트업",
                    style = TerningTheme.typography.body4,
                    color = Grey300,
                    modifier = modifier.padding(bottom = 6.dp)
                )
            }
        }
    }
}