package com.terning.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terning.core.R

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

    Image(
        painter = painterResource(id = option),
        contentDescription = stringResource(id = R.string.sign_up_bottom_sheet_description),
        modifier = modifier
            //.aspectRatio(1f)
            .size(50.dp)
            .clip(shape = CircleShape)
    )
}
