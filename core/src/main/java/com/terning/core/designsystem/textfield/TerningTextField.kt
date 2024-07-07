package com.terning.core.designsystem.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTypography
import com.terning.core.designsystem.theme.White

@Composable
fun TerningTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        textStyle = TerningTypography().button3,
        decorationBox = { innerTextField ->
            Column(modifier = Modifier.drawWithContent {
                drawContent()
                drawLine(
                    color = TerningMain,
                    start = Offset(
                        x = 0f,
                        y = size.height - 1.dp.toPx(),
                    ),
                    end = Offset(
                        x = size.width,
                        y = size.height - 1.dp.toPx(),
                    ),
                    strokeWidth = 2.dp.toPx(),
                )
            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = TerningMain,
                    )
                    innerTextField()
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    )
}