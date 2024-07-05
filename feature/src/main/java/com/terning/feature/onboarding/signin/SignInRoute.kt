package com.terning.feature.onboarding.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.feature.R
import com.terning.feature.onboarding.signin.component.KakaoButton

@Composable
fun SignInRoute() {

    SignInScreen()
}

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_terning_point),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .aspectRatio(1f),
        )
        KakaoButton(
            title = stringResource(id = R.string.sign_in_kakao_button),
            onSignInClick = { onSignInClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}