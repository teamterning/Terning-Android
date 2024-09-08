package com.terning.core.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey500

@Composable
fun TerningBasicSnackBar(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Grey500)
            .padding(vertical = 7.dp, horizontal = 20.dp)
    ) {
        content()
    }
}