package com.luisma.cryptocurrency.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ActiveSign(active: Boolean) {
    if (active)
        AppText.B2("active",
            color = MaterialTheme.colors.primary)
    else
        AppText.B2("inactive", color = Color.Red)
}