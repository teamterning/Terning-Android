package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable
import kotlinx.coroutines.launch

/**
 * 프로필 이미지를 선택할 수 있는 바텀시트입니다.
 *
 * @param modifier 바텀시트에 적용할 Modifier입니다.
 * @param onDismiss 바텀시트가 닫힐 때 호출되는 콜백 함수입니다.
 * @param onSaveClick 저장하기 버튼 클릭 시, 호출되는 콜백 함수입니다.
 * @param initialSelectedOption 초기에 선택된 이미지를 나타내는 인덱스 값입니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSaveClick: (Int) -> Unit,
    initialSelectedOption: Int
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    TerningBasicBottomSheet(
        content = {
            Column(modifier = modifier) {
                Text(
                    text = stringResource(id = R.string.sign_up_bottom_sheet_title),
                    style = TerningTheme.typography.title2,
                    modifier = Modifier
                        .padding(
                            start = 28.dp,
                            bottom = 20.dp
                        ),
                )
                RadioButtonGroup(
                    onOptionSelected = { index ->
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onSaveClick(index)
                                }
                            }
                    },
                    initialSelectedOption = initialSelectedOption
                )
                Spacer(modifier = modifier.padding(bottom = 26.dp))
            }
        },
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    )
}

/**
 * 6개의 프로필 이미지 중, 하나의 이미지만 선택할 수 있는 라디오 버튼입니다.
 *
 * @param modifier 라디오 버튼에 적용할 Modifier입니다.
 * @param onOptionSelected 선택된 이미지의 인덱스 값을 나타내는 콜백 함수입니다.
 * @param initialSelectedOption 초기에 선택된 이미지를 나타내는 인덱스 값입니다.
 */
@Composable
fun RadioButtonGroup(
    modifier: Modifier = Modifier,
    onOptionSelected: (Int) -> Unit,
    initialSelectedOption: Int
) {
    val options = listOf(
        R.drawable.ic_terning_profile_00,
        R.drawable.ic_terning_profile_01,
        R.drawable.ic_terning_profile_02,
        R.drawable.ic_terning_profile_03,
        R.drawable.ic_terning_profile_04,
        R.drawable.ic_terning_profile_05
    )

    var selectedOption by rememberSaveable { mutableIntStateOf(options[initialSelectedOption]) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.padding(horizontal = 34.dp)
    ) {
        itemsIndexed(options) { index, option ->
            val imageModifier = if (selectedOption == options[index]) {
                modifier.border(
                    color = TerningMain,
                    width = 2.dp,
                    shape = CircleShape
                )
            } else {
                modifier
            }

            Image(
                painter = painterResource(
                    id = option
                ),
                contentDescription = "profile image",
                modifier = imageModifier
                    .noRippleClickable {
                        onOptionSelected(index)
                        selectedOption = option
                    }
                    .clip(shape = CircleShape)
            )
        }
    }
}