package com.terning.core.designsystem.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable
import com.terning.core.type.ProfileImage
import com.terning.core.type.SelectedProfileImage
import kotlinx.coroutines.launch

/**
 * 프로필 이미지를 선택할 수 있는 바텀시트입니다.
 *
 * @param modifier 바텀시트에 적용할 Modifier입니다.
 * @param onDismiss 바텀시트가 닫힐 때 호출되는 콜백 함수입니다.
 * @param onSaveClick 저장하기 버튼 클릭 시, 호출되는 콜백 함수입니다.
 * @param initialSelectedOption 초기에 선택된 이미지의 이름을 나타내는 string 값입니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSaveClick: (ProfileImage) -> Unit,
    initialSelectedOption: String
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
                    onOptionSelected = { selectedProfile ->
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onSaveClick(selectedProfile)
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
 * @param onOptionSelected 선택된 이미지의 이름을 나타내는 콜백 함수입니다.
 * @param initialSelectedOption 초기에 선택된 이미지의 이름을 나타내는 string 값입니다.
 * @param modifier 라디오 버튼에 적용할 Modifier입니다.
 */
@Composable
fun RadioButtonGroup(
    onOptionSelected: (ProfileImage) -> Unit,
    initialSelectedOption: String,
    modifier: Modifier = Modifier,
) {
    val options = ProfileImage.entries
    var selectedOptionIndex by rememberSaveable {
        mutableIntStateOf(ProfileImage.toIndex(ProfileImage.fromString(initialSelectedOption)))
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.padding(horizontal = 34.dp)
    ) {
        itemsIndexed(options.take(6)) { index, option ->
            val isSelected = selectedOptionIndex == index
            val imageResId = when {
                isSelected -> SelectedProfileImage.entries[index].drawableResId
                else -> option.drawableResId
            }

            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "profile image",
                modifier = Modifier
                    .aspectRatio(1f)
                    .noRippleClickable {
                        onOptionSelected(option)
                        selectedOptionIndex = index
                    }
                    .clip(shape = CircleShape)
                    .size(76.dp),
            )
        }
    }
}
