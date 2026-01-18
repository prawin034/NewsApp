package com.skyapp.newsapp.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.LoadedFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlin.math.max


/*
    Texts
      1. AppTxtTitle
      2. AppTxtSectionHeader - done
      3. AppTxtShowMore - done
      4. AppTxtHeader
      5. CardHeaderName - done
      6. AppTxtBody
      7. AppTxtBody2
      8. AppTxtBody3



 */


@Composable
fun AppSectionTextHeader(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 19.5.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    fontFamily: FontFamily = FontFamily.Cursive,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit = 3.sp,
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
        fontFamily = fontFamily,


    )
}


@Composable
fun AppTxtShowMore(
    title: String,
    fontSize: TextUnit = 10.5.sp,
    color: Color = Color.LightGray,
    onClick : () -> Unit
) {

    Text(
        text = title.uppercase(),
        fontSize = fontSize,
        fontWeight = FontWeight.SemiBold,
        //fontFamily = FontFamily.SansSerif,
        fontFamily = FontFamily.Default,
        overflow = TextOverflow.Ellipsis,
        letterSpacing = 1.2.sp,
        color = color,
        modifier = Modifier.clickable {
            onClick.invoke()
        }
    )
}



@Composable
fun AppCardHeader(
    title: String,
    fontSize: TextUnit = 14.5.sp,
    color: Color = Color.Black,
    fontFamily: FontFamily = FontFamily.SansSerif,
    modifier: Modifier = Modifier,
    thenModifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE

) {

    Text(
        text = title.uppercase(),
        fontSize = fontSize,
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily,
        overflow = TextOverflow.Ellipsis,
        letterSpacing = 1.4.sp,
        color = color,

        maxLines = maxLines,
        modifier = modifier
            .then(thenModifier)
    )
}


@Composable
fun AppTextBody1(
    title: String,
    fontSize: TextUnit = 12.5.sp,
    color: Color = Color.Black,
    fontFamily: FontFamily = FontFamily.Serif,
    modifier: Modifier = Modifier,
    thenModifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE

) {

    Text(
        text = title,
        fontSize = fontSize,
        fontWeight = FontWeight.Medium,
        fontFamily = fontFamily,
        overflow = TextOverflow.Ellipsis,
        letterSpacing = 1.3.sp,
        maxLines = maxLines,
        color = color,
        modifier = modifier
            .then(thenModifier)
    )
}

@Composable
fun AppTextBody2(
    title: String,
    fontSize: TextUnit = 8.sp,
    color: Color = Color.Gray,
    fontFamily: FontFamily = FontFamily.Serif,
    modifier: Modifier = Modifier,
    thenModifier: Modifier = Modifier,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    maxLines: Int = Int.MAX_VALUE

) {

    Text(
        text = title.uppercase(),
        fontSize = fontSize,
        fontWeight = FontWeight.Medium,
        fontFamily = fontFamily,
        overflow = TextOverflow.Visible,
        maxLines = maxLines,
        letterSpacing = 1.sp,
        textAlign = TextAlign.Center,
        color = color,
        onTextLayout = {
            onTextLayout.invoke(it)
        },
        modifier = modifier
            .then(thenModifier)
    )
}