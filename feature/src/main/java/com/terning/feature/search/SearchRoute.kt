package com.terning.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.textfield.NameTextField
import com.terning.core.designsystem.textfield.SearchTextField
import com.terning.core.designsystem.theme.Grey400
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.WarningRed
import com.terning.feature.R

@Composable
fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    var text by remember { mutableStateOf("") }

    // TODO 프로필 스크린 전용으로, 삭제될 코드입니다
    var helperMessage by remember { mutableStateOf(R.string.profile_text_field_helper) }
    var helperIcon by remember { mutableStateOf<Int?>(null) }
    var helperColor by remember { mutableStateOf(Grey400) }
    val specialCharacterPattern = Regex("[!@#\$%^&*(),.?\":{}|<>\\[\\]\\\\/]")

    // TODO 프로필 스크린 전용으로, 삭제될 코드입니다
    fun updateHelper(text: String) {
        helperMessage = when {
            text.isEmpty() -> R.string.profile_text_field_helper
            specialCharacterPattern.containsMatchIn(text) -> R.string.profile_text_field_warning
            text.length <= 12 -> R.string.profile_text_field_check
            else -> R.string.profile_text_field_helper
        }
        helperIcon = when {
            text.isEmpty() -> null
            specialCharacterPattern.containsMatchIn(text) -> R.drawable.ic_warning
            text.length <= 12 -> R.drawable.ic_check
            else -> null
        }
        helperColor = when {
            text.isEmpty() -> Grey400
            specialCharacterPattern.containsMatchIn(text) -> WarningRed
            text.length <= 12 -> TerningMain
            else -> Grey400
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        SearchTextField(
            text = text,
            onValueChange = { newText ->
                text = newText
            },
            hint = stringResource(R.string.search_text_field_hint),
            leftIcon = R.drawable.ic_nav_search
        )

        // TODO 프로필 스크린 전용으로, 삭제될 코드입니다
        NameTextField(
            text = text,
            onValueChange = { newText ->
                text = newText
                updateHelper(newText)
            },
            hint = stringResource(R.string.profile_text_field_hint),
            helperMessage = stringResource(helperMessage),
            helperIcon = helperIcon,
            helperColor = helperColor
        )
    }
}
