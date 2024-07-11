package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.component.button.RoundButton
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSaveClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    TerningBasicBottomSheet(
        content = {
            Column {
                Text(
                    text = stringResource(id = R.string.sign_up_bottom_sheet_title),
                    style = TerningTheme.typography.title2,
                    modifier = modifier
                        .padding(
                            start = 28.dp,
                            bottom = 25.dp
                        ),
                )
                RadioButtonGroup()
                Spacer(modifier = modifier.padding(bottom = 24.dp))
                RoundButton(
                    style = TerningTheme.typography.button0,
                    paddingVertical = 19.dp,
                    cornerRadius = 10.dp,
                    text = R.string.sign_up_dialog_start,
                    onButtonClick = {
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onSaveClick()
                                }
                            }
                    },
                    modifier = modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = modifier.padding(bottom = 15.dp))
            }
        },
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    )
}

@Composable
fun RadioButtonGroup(
    modifier: Modifier = Modifier
) {
    val options = listOf(
        R.drawable.ic_character1,
        R.drawable.ic_character2,
        R.drawable.ic_character3,
        R.drawable.ic_character4,
        R.drawable.ic_character5,
        R.drawable.ic_character6
    )

    var selectedOption by rememberSaveable { mutableIntStateOf(options[0]) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .padding(horizontal = 42.dp)
    ) {
        items(options) { option ->
            Image(
                painter = painterResource(
                    id = if (option == selectedOption) R.drawable.ic_selected_character
                    else option
                ),
                contentDescription = stringResource(id = R.string.sign_up_bottom_sheet_description),
                modifier = modifier
                    .aspectRatio(1f)
                    .noRippleClickable {
                        selectedOption = option
                    }
            )
        }
    }
}
