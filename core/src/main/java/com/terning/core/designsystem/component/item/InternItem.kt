package com.terning.core.designsystem.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.terning.core.R
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
    scrapId: Int = 0,
    onScrapButtonClicked: (Int) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(10.dp),
        verticalAlignment = Alignment.Bottom
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
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.intern_item_d_day, dateDeadline),
                style = TerningTheme.typography.detail0,
                color = TerningMain,
            )
            TwoLineHeightText(
                text = title,
                style = TerningTheme.typography.title5,
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(R.string.intern_item_working_period),
                    style = TerningTheme.typography.detail3,
                    color = Grey400
                )

                Text(
                    text = stringResource(
                        id = R.string.intern_item_working_period_month,
                        workingPeriod
                    ),
                    style = TerningTheme.typography.detail3,
                    color = TerningMain,
                    modifier = Modifier.padding(start = 4.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.BottomEnd
                ) {
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
                            .noRippleClickable {
                                onScrapButtonClicked(scrapId)
                            },
                    )
                }
            }
        }
    }
}

@Composable
fun TwoLineHeightText(
    text: String,
    style: TextStyle,
) {
    val twoLineHeight = with(LocalDensity.current) {
        (style.lineHeight.toDp() * 3) - style.fontSize.toDp()
    }

    Text(
        text = text,
        style = style,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .height(twoLineHeight)
    )
}

@Preview(showBackground = true)
@Composable
fun InternItemPreview() {
    TerningPointTheme {
        InternItem(
            imageUrl = "",
            title = "[Someone] 콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인콘텐츠 마케터 대학생 마케터 대학생 인턴 채용 공고",
            dateDeadline = "3",
            workingPeriod = "6",
            isScraped = true
        )
    }
}