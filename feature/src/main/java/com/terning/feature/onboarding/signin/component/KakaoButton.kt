package com.terning.feature.onboarding.signin.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.extension.noRippleClickable
import com.terning.feature.R


@Composable
fun KakaoButton(
    title: String,
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .noRippleClickable { onSignInClick() }
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_signin_kakao),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = title,
            // TODO : style 추가하기
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KakaoButtonPreview() {
    KakaoButton("카카오로 로그인하기", onSignInClick = {})
}