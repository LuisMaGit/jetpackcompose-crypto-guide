package com.luisma.cryptocurrency.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CenterLoader() {
    Center {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary
        )
    }
}