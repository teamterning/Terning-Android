package com.terning.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = lightColorScheme(
    primary = TerningMain,
    background = White
)

private val LocalTerningTypography = staticCompositionLocalOf<TerningTypography> {
    error("No TerningTypography provided")
}

object TerningTheme {
    val typography: TerningTypography
        @Composable
        get() = LocalTerningTypography.current
}

@Composable
fun ProvideTerningTypography(typography: TerningTypography, content: @Composable () -> Unit) {
    val provideTypography = remember { typography.copy() }
    provideTypography.update(typography)
    CompositionLocalProvider(
        LocalTerningTypography provides provideTypography,
        content = content
    )
}

@Composable
fun TerningPointTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val typography = TerningTypography()

    ProvideTerningTypography(typography = typography) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}