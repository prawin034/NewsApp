package com.skyapp.newsapp.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp





/*
    Texts
      1. AppTxtTitle
      2. AppTxtSectionHeader
      3. AppTxtShowMore
      4. AppTxtHeader
      5. CardHeaderName
      6. AppTxtBody
      7. AppTxtBody2
      8. AppTxtBody3



 */


@Composable
fun AppTextHeader(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 14.0.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit = 2.sp,
    maxLines: Int = Int.MAX_VALUE,

) {


    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        maxLines = maxLines,
        color = textColor,
        textAlign = textAlign,
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize,
            fontFamily = FontFamily.Default
        )

    )
}


@Composable
fun CardHeaderName(
    title: String,
    fontSize: Int = 10,
    color: Color = Color.LightGray
) {

    Text(
        text = title.uppercase(),
        fontSize = fontSize.sp,
        fontWeight = FontWeight.SemiBold,
        //fontFamily = FontFamily.SansSerif,
        fontFamily = FontFamily.Default,
        overflow = TextOverflow.Ellipsis,
        letterSpacing = 1.sp,
        color = color
    )
}
