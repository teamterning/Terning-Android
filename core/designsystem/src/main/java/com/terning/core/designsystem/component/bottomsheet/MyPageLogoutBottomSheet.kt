package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.R
import com.terning.core.designsystem.component.button.DeleteRoundButton
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningTheme
import kotlinx.coroutines.launch

/**
 * 로그아웃을 할 수 있는 바텀시트입니다.
 *
 * 로그아웃 버튼과, 취소 버튼으로 이루어져있습니다.
 *
 * @param modifier 바텀시트에 적용할 Modifier입니다.
 * @param onDismiss 취소 버튼 클릭 시, 바텀시트가 닫히면서 호출되는 함수입니다.
 * @param onLogoutClick 로그아웃 버튼 클릭 시, 호출되는 콜백 함수입니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageLogoutBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    TerningBasicBottomSheet(
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.my_page_bottom_sheet_title),
                    style = TerningTheme.typography.heading1,
                )
                Spacer(modifier = modifier.height(60.dp))
                Text(
                    text = stringResource(id = R.string.my_page_logout_sub),
                    style = TerningTheme.typography.body4,
                    color = Grey400,
                )
                Spacer(modifier = modifier.height(64.dp))
                RoundButton(
                    style = TerningTheme.typography.button2,
                    paddingVertical = 15.dp,
                    cornerRadius = 10.dp,
                    text = R.string.my_page_logout_button,
                    onButtonClick = {
                        onLogoutClick()
                    },
                    modifier = modifier.padding(
                        start = 24.dp,
                        end = 24.dp
                    ),
                )
                Spacer(modifier = modifier.height(8.dp))
                DeleteRoundButton(
                    style = TerningTheme.typography.button2,
                    paddingVertical = 15.dp,
                    cornerRadius = 10.dp,
                    text = R.string.my_page_back_button,
                    onButtonClick = {
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onDismiss()
                                }
                            }
                    },
                    modifier = modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                    )
                )
                Spacer(modifier = modifier.height(32.dp))
            }
        },
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    )
}