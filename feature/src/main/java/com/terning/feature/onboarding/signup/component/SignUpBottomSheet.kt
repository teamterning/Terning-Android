package com.terning.feature.onboarding.signup.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.component.bottomsheet.TerningBasicBottomSheet
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.core.extension.noRippleClickable

@Composable
fun SignUpBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    TerningBasicBottomSheet(
        content = {
            Column {
                Text(
                    text = stringResource(id = R.string.bottom_sheet_title),
                    style = TerningTheme.typography.title2,
                    modifier = modifier.padding(start = 28.dp)
                )
                RadioButtonGroup()
            }
        },
        onDismissRequest = { onDismiss() }
    )
}

@Composable
fun RadioButtonGroup() {
    val options = listOf(
        R.drawable.ic_character1,
        R.drawable.ic_character2,
        R.drawable.ic_character3,
        R.drawable.ic_character4
    )

    var selectedOption by rememberSaveable { mutableIntStateOf(options[0]) }

    Column {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 28.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = if (option == selectedOption) R.drawable.ic_selected_character else option),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .noRippleClickable {
                            selectedOption = option
                        }
                )
            }
        }
    }
}
