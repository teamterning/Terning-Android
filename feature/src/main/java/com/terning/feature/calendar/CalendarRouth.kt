package com.terning.feature.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalendarRoute() {
    CalendarScreen()
}

@Composable
fun CalendarScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "캘린더 스크린")
    }
}