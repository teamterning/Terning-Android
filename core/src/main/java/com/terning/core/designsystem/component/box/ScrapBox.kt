package com.terning.core.designsystem.component.box

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.terning.core.designsystem.theme.White

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
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = scrapColor,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .fillMaxSize()
        )
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScrapBoxPreview() {
    Column {
        ScrapBox(
            modifier = Modifier
                .height(116.dp)
                .width(140.dp),
            scrapColor = CalPink,
            cornerRadius = 5.dp,
            borderWidth = 1.dp
        ) {}

        Spacer(modifier = Modifier.height(10.dp))

        ScrapBox(
            modifier = Modifier
                .height(height = 92.dp)
                .fillMaxWidth(),
            scrapColor = CalPurple,
            cornerRadius = 10.dp,
            elevation = 1.dp
        ) {}
    }
}