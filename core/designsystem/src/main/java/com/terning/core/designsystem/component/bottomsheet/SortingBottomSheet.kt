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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.R
import com.terning.core.designsystem.extension.noRippleClickable
import com.terning.core.designsystem.theme.Black
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.designsystem.type.SortBy
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingBottomSheet(
    onDismiss: () -> Unit,
    currentSortBy: Int,
    modifier: Modifier = Modifier,
    newSortBy: MutableState<Int> = mutableIntStateOf(currentSortBy),
    onSortChange: (Int) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    TerningBasicBottomSheet(
        content = {
            Text(
                text = stringResource(id = R.string.sort_bottom_sheet_title),
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
                items(sortByCount) { sortIndex ->
                    Text(
                        text = stringResource(id = SortBy.entries[sortIndex].sortBy),
                        style = TerningTheme.typography.button3,
                        color = if (currentSortBy == sortIndex) TerningMain else Grey400,
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 3.dp)
                            .padding(vertical = 12.dp)
                            .noRippleClickable {
                                newSortBy.value = sortIndex
                                onSortChange(sortIndex)
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
        onDismissRequest = onDismiss,
        sheetState = sheetState
    )
}

private const val sortByCount = 5