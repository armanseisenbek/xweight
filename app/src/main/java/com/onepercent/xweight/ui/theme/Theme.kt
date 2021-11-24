package com.onepercent.xweight.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = darkThemePrimary,
    onPrimary = darkThemeOnPrimary,

    secondary = darkThemeSecondary,
    onSecondary = darkThemeOnSecondary,

    background = darkThemeBackground,
    onBackground = darkThemeOnBackground,

    surface = darkThemeSurface,
    onSurface = darkThemeOnSurface,
)

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    onPrimary = Color.White,

    secondary = Secondary,
    secondaryVariant = SecondaryDark,
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
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}