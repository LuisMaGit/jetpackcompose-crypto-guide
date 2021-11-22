package com.luisma.cryptocurrency.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.luisma.cryptocurrency.ui.theme.AppTextType


abstract class AppText {
    companion object {
        @Composable
        fun H1(
            text: String,
            modifier: Modifier = Modifier,
            maxLines: Int = Int.MAX_VALUE,
            overflow: TextOverflow = TextOverflow.Ellipsis,
            color: Color = MaterialTheme.colors.secondary,
            textAlign: TextAlign? = AppTextType.h1.textAlign,
            fontSize: TextUnit = AppTextType.h1.fontSize,
            fontWeight: FontWeight? = AppTextType.h1.fontWeight,
            fontFamily: FontFamily? = AppTextType.h1.fontFamily,
        ) {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                maxLines = maxLines,
            )
        }

        @Composable
        fun H2(
            text: String,
            modifier: Modifier = Modifier,
            overflow: TextOverflow = TextOverflow.Ellipsis,
            maxLines: Int = Int.MAX_VALUE,
            color: Color = MaterialTheme.colors.secondary,
            fontSize: TextUnit = AppTextType.h2.fontSize,
            fontWeight: FontWeight? = AppTextType.h2.fontWeight,
            fontFamily: FontFamily? = AppTextType.h2.fontFamily,
            textAlign: TextAlign? = AppTextType.h1.textAlign,
        ) {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                maxLines = maxLines,
            )
        }

        @Composable
        fun B1(
            text: String,
            modifier: Modifier = Modifier,
            overflow: TextOverflow = TextOverflow.Ellipsis,
            maxLines: Int = Int.MAX_VALUE,
            color: Color = MaterialTheme.colors.secondary,
            fontSize: TextUnit = AppTextType.body1.fontSize,
            fontWeight: FontWeight? = AppTextType.body1.fontWeight,
            fontFamily: FontFamily? = AppTextType.body1.fontFamily,
            textAlign: TextAlign? = AppTextType.h1.textAlign,
        ) {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                maxLines = maxLines,
            )
        }

        @Composable
        fun B2(
            text: String,
            modifier: Modifier = Modifier,
            overflow: TextOverflow = TextOverflow.Ellipsis,
            maxLines: Int = Int.MAX_VALUE,
            color: Color = MaterialTheme.colors.secondary,
            fontSize: TextUnit = AppTextType.body2.fontSize,
            fontWeight: FontWeight? = AppTextType.body2.fontWeight,
            fontFamily: FontFamily? = AppTextType.body2.fontFamily,
            textAlign: TextAlign? = AppTextType.h1.textAlign,
        ) {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                maxLines = maxLines,
            )
        }
        @Composable
        fun Caption(
            text: String,
            modifier: Modifier = Modifier,
            overflow: TextOverflow = TextOverflow.Ellipsis,
            maxLines: Int = Int.MAX_VALUE,
            color: Color = MaterialTheme.colors.secondaryVariant,
            fontSize: TextUnit = AppTextType.caption.fontSize,
            fontWeight: FontWeight? = AppTextType.caption.fontWeight,
            fontFamily: FontFamily? = AppTextType.caption.fontFamily,
            textAlign: TextAlign? = AppTextType.h1.textAlign,
        ) {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                maxLines = maxLines,
            )
        }
    }
}