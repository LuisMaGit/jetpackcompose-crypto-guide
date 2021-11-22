package com.luisma.cryptocurrency.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FractionallyPageWrapper(
    modifier: Modifier = Modifier,
    fraction: Float = .95F,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth(fraction = fraction)
    ) {
        content()
    }
}