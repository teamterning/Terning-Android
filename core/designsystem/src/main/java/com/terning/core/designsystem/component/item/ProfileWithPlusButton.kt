package com.terning.core.designsystem.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.R
import com.terning.core.designsystem.type.ProfileImage

@Composable
fun ProfileWithPlusButton(
    profileImage: String,
    modifier: Modifier = Modifier,
) {
    val userProfile = ProfileImage.fromString(profileImage)

    Box(
        modifier = modifier.wrapContentWidth()
    ) {
        Image(
            painterResource(id = userProfile.drawableResId),
            contentDescription = "profile image",
            modifier = modifier
                .clip(shape = CircleShape)
                .size(80.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.ic_sign_up_button),
            contentDescription = "plus button",
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileWithPlusButtonPreview() {
    ProfileWithPlusButton(profileImage = "basic")
}