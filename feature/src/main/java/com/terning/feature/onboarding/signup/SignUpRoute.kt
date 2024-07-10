package com.terning.feature.onboarding.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.TerningPointTheme
import com.terning.core.designsystem.theme.TerningTheme
import com.terning.feature.R
import com.terning.feature.onboarding.signup.component.SignUpProfile

@Composable
fun SignUpRoute() {
    SignUpScreen()
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = TerningTheme.typography.heading2,
            modifier = modifier.padding(
                bottom = 42.dp,
                start = 24.dp
            )
        )
        Text(
            text = stringResource(id = R.string.sign_up_profile_image),
            style = TerningTheme.typography.body2,
            modifier = modifier.padding(start = 24.dp)
        )
        Spacer(modifier = modifier.weight(2f))
        SignUpProfile()
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    TerningPointTheme {
        SignUpScreen()
    }
}
