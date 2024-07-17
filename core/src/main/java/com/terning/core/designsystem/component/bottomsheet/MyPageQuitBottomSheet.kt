package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.component.button.DeleteRoundButton
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.theme.TerningTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageQuitBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onQuitClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    TerningBasicBottomSheet(
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.my_page_bottom_sheet_title),
                    style = TerningTheme.typography.heading1,
                    modifier = modifier.padding(top = 14.dp)
                )
                Text(
                    text = stringResource(id = R.string.my_page_quit_sub),
                    style = TerningTheme.typography.body3,
                    modifier = modifier.padding(top = 54.dp),
                    textAlign = TextAlign.Center
                )
                RoundButton(
                    style = TerningTheme.typography.button2,
                    paddingVertical = 15.dp,
                    cornerRadius = 10.dp,
                    text = R.string.my_page_quit_button,
                    onButtonClick = {
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onQuitClick()
                                }
                            }
                    },
                    modifier = modifier.padding(
                        start = 24.dp,
                        top = 41.dp,
                        end = 24.dp
                    )
                )
                DeleteRoundButton(
                    style = TerningTheme.typography.button2,
                    paddingVertical = 15.dp,
                    cornerRadius = 10.dp,
                    text = R.string.my_page_back_button,
                    onButtonClick = {
                        onDismiss()
                    },
                    modifier = modifier.padding(
                        start = 24.dp,
                        top = 8.dp,
                        end = 24.dp,
                        bottom = 100.dp
                    )
                )
            }
        },
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    )
}
