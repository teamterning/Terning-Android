package com.terning.core.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.terning.core.R
import com.terning.core.designsystem.theme.Grey300
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.noRippleClickable

/**
 * 앱에서 사용하는 다이얼로그의 기본 프레임입니다.
 *
 * @param onDismissRequest 사용자가 다이얼로그를 닫을 때 호출되는 콜백입니다.
 * @param properties 다이얼로그의 속성입니다.
 * @param content 다이얼로그의 내용을 구성하는 컴포저블 컨텐츠입니다.
 */

@Composable
fun TerningBasicDialog(
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
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 27.dp)
                .background(
                    color = White,
                    shape = RoundedCornerShape(20.dp)
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dialog_x_32),
                    contentDescription = null,
                    tint = Grey300,
                    modifier = Modifier
                        .noRippleClickable { onDismissRequest() }
                )
            }
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TerningBasicDialogPreview() {
    TerningBasicDialog(
        onDismissRequest = {},
    ) {

    }
}