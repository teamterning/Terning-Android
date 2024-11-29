package com.terning.core.designsystem.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.CalPink
import com.terning.core.designsystem.theme.CalPurple
import com.terning.core.designsystem.theme.Grey150
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White

/**
 * 홈과 달력 화면에서 스크랩된 공고 목록을 표시하기 위해 사용하는 컴포넌트입니다.
 *
 * @param cornerRadius 코너 둥근 정도입니다.
 * @param scrapColor 좌측 공간의 색상입니다.
 * @param modifier 수정자입니다.
 * @param elevation 그림자입니다.
 * @param borderWidth 테두리 두께입니다.
 * @param borderColor 테두리 색상입니다.
 * @param content 컴포저블 내용입니다.
 */

@Composable
fun ScrapBox(
    cornerRadius: Dp,
    scrapColor: Color,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    borderWidth: Dp = 0.dp,
    borderColor: Color = Grey150,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .border(
                width = borderWidth,
                color = borderColor,
                RoundedCornerShape(cornerRadius),
            )
            .shadow(
                elevation = elevation,
                RoundedCornerShape(cornerRadius),
            )
            .background(
                color = scrapColor,
                shape = RoundedCornerShape(cornerRadius)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 9.dp)
                .background(
                    shape = RoundedCornerShape(
                        topEnd = cornerRadius, bottomEnd = cornerRadius
                    ), color = White
                )
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BorderedScrapBoxPreview() {
    ScrapBox(
        modifier = Modifier
            .height(116.dp)
            .width(140.dp),
        scrapColor = CalPink,
        cornerRadius = 5.dp,
        borderWidth = 1.dp
    ) {}
}

@Preview(showBackground = true)
@Composable
fun ShadowedScrapBoxPreview() {
    TerningPointTheme {
        ScrapBox(
            modifier = Modifier,
            scrapColor = CalPurple,
            cornerRadius = 10.dp,
            elevation = 1.dp
        ) {
            InternItem(
                imageUrl = "",
                title = "[Someone] 콘텐츠 마케터 대학생",
                dateDeadline = "3",
                workingPeriod = "3",
                isScraped = false
            )
        }
    }
}