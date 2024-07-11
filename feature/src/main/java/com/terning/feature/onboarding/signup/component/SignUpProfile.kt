package com.terning.feature.onboarding.signup.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.terning.feature.R

@Composable
fun SignUpProfile(
    modifier: Modifier = Modifier
) {
    Box {
        Image(
            painterResource(id = R.drawable.ic_sign_up_profile),
            contentDescription = stringResource(id = R.string.sign_up_profile_image)
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
    SignUpProfile()
}