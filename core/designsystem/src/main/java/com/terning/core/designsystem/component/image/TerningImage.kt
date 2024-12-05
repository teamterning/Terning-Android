package com.terning.core.designsystem.component.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.terning.core.designsystem.R
import com.terning.core.designsystem.theme.TerningPointTheme

/**
 * contentDescription을 사용하지 않고 Image를 편리하게 사용할 수 있는 함수입니다.
 *
 * @param painter 이미지 리소스 ID입니다.
 * @param modifier Image의 Modifier입니다.
 */
@Composable
fun TerningImage(
    painter: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = painter),
        contentDescription = stringResource(id = R.string.image_content_descriptin),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TerningBasicImagePreview() {
    TerningPointTheme {
        TerningImage(
            painter = R.drawable.ic_back
        )
    }
}