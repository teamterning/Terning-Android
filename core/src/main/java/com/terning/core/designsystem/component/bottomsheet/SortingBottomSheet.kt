package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    currentSortBy: Int,
    newSortBy: MutableState<Int> = mutableStateOf(currentSortBy),
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var currentSortBy by remember { mutableStateOf(currentSortBy) }

    TerningBasicBottomSheet(
        content = {
            Text(
                text = "공고 정렬 순서",
                style = TerningTheme.typography.title2,
                color = Black,
                modifier = modifier
                    .padding(start = 27.dp, bottom = 16.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Grey200,
                modifier = modifier.padding(horizontal = 24.dp)
            )

            LazyColumn(
                modifier = modifier
                    .padding(top = 12.dp, bottom = 19.dp)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(5) { sortIndex ->
                    Text(
                        text = stringResource(id = SortBy.entries[sortIndex].type),
                        style = TerningTheme.typography.button3,
                        color = if (currentSortBy == sortIndex) TerningMain else Grey400,
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 3.dp)
                            .padding(vertical = 12.dp)
                            .noRippleClickable {
                                newSortBy.value = sortIndex
                                scope
                                    .launch { sheetState.hide() }
                                    .invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            onDismiss()
                                        }
                                    }
                            }
                    )
                }
            }
        },
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    )
}