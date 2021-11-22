package com.luisma.cryptocurrency.ui.views.themeWrapper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.luisma.cryptocurrency.ui.theme.*


private val DarkColorPalette = darkColors(
    primary = GreenLight,
    onPrimary = GreenDark,
    background = Black,
    onBackground = BlackLight,
    secondary = White,
    onSecondary = WhiteDarker
)

private val LightColorPalette = lightColors(
    primary = GreenDark,
    onPrimary = GreenLight,
    background = WhiteDarker,
    onBackground = White,
    secondary = Black,
    onSecondary = BlackLight
)


@Composable
fun ThemeWrapper(
    model: ThemeWrapperViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val colors = if (model.darkTheme.value) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = AppTextType,
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                content()
            }
        }
    )
}