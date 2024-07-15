package com.terning.core.designsystem.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.terning.core.designsystem.theme.Grey200
import com.terning.core.designsystem.theme.White
import com.terning.core.extension.customShadow

@Composable
fun InternItemWithShadow(
    imageUrl: String,
    title: String,
    dateDeadline: String,
    workingPeriod: String,
    isScraped: Boolean,
) {
    Box(
        modifier = Modifier
            .customShadow(
                color = Grey200,
                shadowRadius = 10.dp,
                shadowWidth = 2.dp
            )
            .background(
                color = White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        InternItem(
            imageUrl = imageUrl,
            title = title,
            dateDeadline = dateDeadline,
            workingPeriod = workingPeriod,
            isScraped = isScraped
        )
    }
}