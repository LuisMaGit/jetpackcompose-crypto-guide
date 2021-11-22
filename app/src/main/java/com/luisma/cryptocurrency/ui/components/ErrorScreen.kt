package com.luisma.cryptocurrency.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luisma.cryptocurrency.ui.theme.White
import com.luisma.cryptocurrency.ui.views.themeWrapper.ThemeWrapper

@Composable
fun ErrorScreen(onTap: () -> Unit) {
    Center {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText.H1("Error")
            SpacerV.Medium()
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.Red)
                    .padding(all = 16.dp)
                    .clickable { onTap() }
            ) {
                AppText.B1(
                    "Inténtelo de nuevo",
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ThemeWrapper {
        ErrorScreen {}
    }
}