package com.terning.feature.mypage.mypage.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.TerningMain
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.White

@Composable
internal fun MyPageToggleButton(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        modifier = Modifier,
        checked = isChecked,
        onCheckedChange = { checked ->
            onCheckedChange(checked)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = White,
            checkedTrackColor = TerningMain,
            uncheckedThumbColor = White,
            uncheckedTrackColor = Grey200,
            uncheckedBorderColor = Grey200
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun MyPageToggleButtonPreview() {
    TerningPointTheme {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            MyPageToggleButton(
                isChecked = true,
                onCheckedChange = {}
            )
            MyPageToggleButton(
                isChecked = false,
                onCheckedChange = {}
            )
        }
    }
}