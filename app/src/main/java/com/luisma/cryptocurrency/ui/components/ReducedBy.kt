package com.luisma.cryptocurrency.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ReducedBy(
    modifier: Modifier = Modifier,
    reduce: Dp = 0.dp,
    fractionWidth: Float = 1F,
    composable: @Composable () -> Unit = {},
) {

    val w =
        (LocalContext.current.resources.displayMetrics.widthPixels.dp /
                LocalContext.current.resources.displayMetrics.density)

    Box(modifier = modifier.width(w.times(fractionWidth) - reduce)) {
        composable()
    }
}
