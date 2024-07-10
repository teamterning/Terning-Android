package com.terning.core.designsystem.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.buildSpannedString
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.R
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable

@Composable
fun InternItem(
    imageUrl: String,
    title: String,
    dateDeadline: String,
    workingPeriod: String,
    isScraped: Boolean,
    modifier: Modifier = Modifier,
    onScrapButtonClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = title,
            Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(5.dp))
                .background(color = Grey300)
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(id = R.string.intern_item_d_day, dateDeadline),
                style = TerningTheme.typography.detail0,
                color = TerningMain,
            )

            Text(
                text = title,
                style = TerningTheme.typography.title5,
                color = Black,
                softWrap = true,
                modifier = modifier.padding(top = 3.dp),
            )

            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(R.string.intern_item_working_period),
                    style = TerningTheme.typography.detail3,
                    color = Grey400
                )

                Text(
                    text = stringResource(id = R.string.intern_item_working_period_month, workingPeriod),
                    style = TerningTheme.typography.detail3,
                    color = TerningMain,
                    modifier = modifier
                        .padding(start = 4.dp)
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(
                    id =
                    if (isScraped)
                        R.drawable.ic_bookmark_filled
                    else
                        R.drawable.ic_bookmark_outlined
                ),
                contentDescription = stringResource(id = R.string.intern_item_scrap),
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .noRippleClickable {
                        onScrapButtonClicked()
                    },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InternItemPreview() {
    TerningPointTheme {
        Box(
            modifier = Modifier
                .height(height = 92.dp)
                .fillMaxWidth(),
        ){
            InternItem(
                imageUrl = "",
                title = "test title",
                dateDeadline = "3",
                workingPeriod = "6",
                isScraped = true
            )
        }
    }
}