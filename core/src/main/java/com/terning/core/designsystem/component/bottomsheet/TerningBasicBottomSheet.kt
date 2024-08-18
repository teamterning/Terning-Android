package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.terning.core.designsystem.theme.White

/**
 * 기본 바텀시트 함수입니다.
 *
 * @param content 바텀시트에 표시할 내용을 담은 Composable 함수입니다.
 * @param onDismissRequest 바텀시트가 닫히는 요청 시 호출되는 콜백 함수입니다.
 * @param sheetState 바텀시트의 상태를 관리하는 SheetState 객체입니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerningBasicBottomSheet(
    content: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState,
        containerColor = White,
    ) {
        content()
    }
}