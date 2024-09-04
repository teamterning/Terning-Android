package com.terning.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.terning.core.R

@Composable
fun MyPageProfile(
    modifier: Modifier = Modifier,
    profile: Int
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
        0 -> options[0]
        1 -> options[1]
        2 -> options[2]
        3 -> options[3]
        4 -> options[4]
        else -> options[5]
    }

    Image(
        painter = painterResource(id = option),
        modifier = modifier
            .size(72.dp)
            .clip(shape = CircleShape)
            .aspectRatio(1f),
        contentDescription = "profile image"
    )
}
