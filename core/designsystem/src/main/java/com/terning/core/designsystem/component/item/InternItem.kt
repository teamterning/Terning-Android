package com.terning.core.designsystem.component.item

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.terning.core.designsystem.R
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme

/**
 * 홈, 달력, 검색 화면에서 공고를 나타낼 때 사용되는 뷰입니다.
 *
 * @param imageUrl 공고 이미지 URL입니다.
 * @param title 공고 제목입니다.
 * @param dateDeadline 공고 기한입니다.
 * @param workingPeriod 공고 근무 기간입니다.
 * @param isScraped 스크랩 여부입니다.
 * @param modifier 수정자입니다.
 * @param scrapId 공고 아이디입니다.
 * @param onScrapButtonClicked 스크랩 버튼 클릭 시 호출되는 콜백입니다.
 */

@Composable
fun InternItem(
    imageUrl: String,
    title: String,
    dateDeadline: String,
    workingPeriod: String,
    isScraped: Boolean,
    modifier: Modifier = Modifier,
    scrapId: Long = 0,
    isApplyClosed: Boolean = false,
    onScrapButtonClicked: (Long) -> Unit = {},
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_terning_intern_item_image_loading_76),
            contentDescription = title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(5.dp))
                .then(
                    if (isApplyClosed) Modifier.alpha(0.5f)
                    else Modifier
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Text(
                text = dateDeadline,
                style = TerningTheme.typography.detail0,
                color = when {
                    isApplyClosed -> Grey350
                    dateDeadline == stringResource(id = R.string.intern_apply_closed) -> Grey300
                    else -> TerningMain
                },
            )
            TwoLineHeightText(
                text = title,
                style = TerningTheme.typography.title5,
                color = if (isApplyClosed) Grey350 else Black,
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(R.string.intern_item_working_period),
                    style = TerningTheme.typography.detail3,
                    color = if (isApplyClosed) Grey350 else Grey400,
                )

                Text(
                    text = workingPeriod,
                    style = TerningTheme.typography.detail3,
                    color = if (isApplyClosed) Grey350 else TerningMain,
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
    color: Color = Black,
) {
    val twoLineHeight = with(LocalDensity.current) {
        (style.lineHeight.toDp() * 3) - style.fontSize.toDp()
    }

    Text(
        text = text,
        style = style,
        color = color,
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
            imageUrl = "https://media-cdn.linkareer.com/activity_manager/logos/519245",
            title = "영상 디자이너 (모션그래픽) (인턴/1년)",
            dateDeadline = "D-13",
            workingPeriod = "3개월",
            isScraped = true
        )
    }
}