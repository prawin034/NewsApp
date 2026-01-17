package com.skyapp.newsapp.ui.common

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize


fun Modifier.shimmerEffect(shape: RoundedCornerShape) : Modifier = composed {


    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmerEffect")
    val startOffset by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat() ,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000)
        ),
        label =  "shimmerEffect"
    )



    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFE6E2E2),
                Color(0xFFB8B5B5),
                Color(0xFFB8B5B5)
            ),
            start = Offset(startOffset,0f),
            end =  Offset(startOffset + size.width.toFloat(), y = size.height.toFloat())
        ),
        shape = shape
    )
        .onGloballyPositioned {
            size = it.size
        }



}