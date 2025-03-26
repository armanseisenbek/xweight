package com.onepercent.xweight.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = darkThemePrimary,
    onPrimary = darkThemeOnPrimary,

    secondary = darkThemeSecondary,
    onSecondary = darkThemeOnSecondary,

    background = darkThemeBackground,
    onBackground = darkThemeOnBackground,

    surface = darkThemeSurface,
    onSurface = darkThemeOnSurface,
)

private val LightColorPalette = lightColorScheme(
    primary = Primary,
    primaryContainer = PrimaryDark,
    onPrimary = Color.White,

    secondary = Secondary,
    secondaryContainer = SecondaryDark,
    onSecondary = Color.White,

    background = Color.White,
    onBackground = Color.Black,

    surface = Color.White,
    onSurface = Color.Black,
)

@Composable
fun XweightTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}