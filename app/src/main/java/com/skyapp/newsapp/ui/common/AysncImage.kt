package com.skyapp.newsapp.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun AppAsyncImg(
    imageUrl : Any?,
    contentDescription : String? = "",
    modifier: Modifier = Modifier.fillMaxWidth().animateContentSize(),
    height : Dp = 260.dp, //default ,
    onClick : () -> Unit
){
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        filterQuality = FilterQuality.Low,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(height)
            .clickable {
                onClick.invoke()
            },

    )
}