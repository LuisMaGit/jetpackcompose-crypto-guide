package com.luisma.cryptocurrency.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.luisma.cryptocurrency.R

@Composable
fun ActiveSign(active: Boolean) {
    if (active)
        AppText.B2(stringResource(id = R.string.components_active_sign),
            color = MaterialTheme.colors.primary)
    else
        AppText.B2(stringResource(id = R.string.components_inactive_sign), color = Color.Red)
}