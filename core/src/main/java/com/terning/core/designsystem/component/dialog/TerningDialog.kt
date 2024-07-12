package com.terning.core.designsystem.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.terning.core.R
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.Grey350
import com.terning.core.designsystem.theme.Grey500
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.theme.White

@Composable
fun TerningDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(
        usePlatformDefaultWidth = false,
        decorFitsSystemWindows = true,
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
    ),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = properties,
    ) {
        content()
    }
}

@Composable
fun DialogContent(
    onDismissRequest: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(30.dp)
            .background(
                color = White,
                shape = RoundedCornerShape(20.dp)
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { onDismissRequest() }, modifier = Modifier.padding(6.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dialog_x_32),
                    contentDescription = null,
                    tint = Grey300
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 11.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .border(
                            width = 1.dp,
                            color = TerningMain,
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_character1
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Grey200,
                                shape = RoundedCornerShape(size = 15.dp)
                            ),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )
                }
                Text(
                    text = "[한양대학교 컬렉티브임팩트센터] /코이카 영프로페셔널(YP) 모집합니다",
                    textAlign = TextAlign.Center,
                    style = TerningTheme.typography.title4,
                    color = Grey500,
                    modifier = Modifier.padding(top = 20.dp)
                )
                Text(
                    text = "공고를 캘린더에 스크랩하시겠어요?",
                    style = TerningTheme.typography.body5,
                    color = Grey350,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TerningDialogPreview() {
    TerningDialog(
        onDismissRequest = {},
        content = {
            DialogContent(
                onDismissRequest = {}
            )
        })
}