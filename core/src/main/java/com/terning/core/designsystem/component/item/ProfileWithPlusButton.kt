package com.terning.core.designsystem.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.R
import com.terning.core.designsystem.component.image.TerningImage

@Composable
fun ProfileWithPlusButton(
    index: Int,
    modifier: Modifier = Modifier,
) {
    val grade = when (index) {
        0 -> R.drawable.ic_terning_profile_00
        1 -> R.drawable.ic_terning_profile_01
        2 -> R.drawable.ic_terning_profile_02
        3 -> R.drawable.ic_terning_profile_03
        4 -> R.drawable.ic_terning_profile_04
        else -> R.drawable.ic_terning_profile_05
    }

    Box(
        modifier = modifier.wrapContentWidth()
    ) {
        Image(
            painterResource(id = grade),
            contentDescription = null,
            modifier = modifier
                .clip(shape = RoundedCornerShape(76.dp))
                .size(80.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier.align(Alignment.BottomEnd)
        ) {
            TerningImage(painter = R.drawable.ic_sign_up_button)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileWithPlusButtonPreview() {
    ProfileWithPlusButton(index = 1)
}