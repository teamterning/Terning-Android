package com.terning.feature.mypage.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.component.image.TerningImage

@Composable
fun MyPageProfile(
    modifier: Modifier = Modifier,
    profile: String
) {
    val options = listOf(
        R.drawable.ic_terning_profile_00,
        R.drawable.ic_terning_profile_01,
        R.drawable.ic_terning_profile_02,
        R.drawable.ic_terning_profile_03,
        R.drawable.ic_terning_profile_04,
        R.drawable.ic_terning_profile_05
    )

    val option = when (profile) {
        "PROFILE_00" -> options[0]
        "PROFILE_01" -> options[1]
        "PROFILE_02" -> options[2]
        "PROFILE_03" -> options[3]
        "PROFILE_04" -> options[4]
        else -> options[5]
    }

    TerningImage(
        painter = option,
        modifier = modifier
            .size(72.dp)
            .clip(shape = CircleShape)
    )
}
