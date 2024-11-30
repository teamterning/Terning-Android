package com.terning.feature.mypage.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.type.ProfileImage

@Composable
fun MyPageProfile(
    modifier: Modifier = Modifier,
    profileImage: String
) {
    val userProfile = ProfileImage.fromString(profileImage)

    Image(
        painter = painterResource(id = userProfile.drawableResId),
        modifier = modifier
            .size(72.dp)
            .clip(shape = CircleShape)
            .aspectRatio(1f),
        contentDescription = "profile image"
    )
}