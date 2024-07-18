package com.terning.feature.onboarding.signup.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.feature.R

@Composable
fun SignUpProfile(
    modifier: Modifier = Modifier,
    index: Int
) {
    var grade = when (index) {
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
            contentDescription = stringResource(id = R.string.sign_up_profile_image),
            modifier = modifier
                .clip(shape = RoundedCornerShape(76.dp))
                .size(80.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier.align(Alignment.BottomEnd)
        ) {
            Image(
                painterResource(id = R.drawable.ic_sign_up_button),
                contentDescription = stringResource(id = R.string.sign_up_profile_button),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpProfilePreview() {
    SignUpProfile(index = 1)
}