package com.skyapp.newsapp.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


@Composable
fun AppBottomBar(
    modifier : Modifier = Modifier,
    containerColor : Color = BottomAppBarDefaults.containerColor,
    contentColor : Color = contentColorFor(containerColor),
    tonalElevation : Dp = BottomAppBarDefaults.ContainerElevation,
    contentPadding : PaddingValues = BottomAppBarDefaults.ContentPadding,
    content : @Composable () -> Unit
) {

    BottomAppBar(
        containerColor = containerColor,
        contentColor = contentColor,
        contentPadding = contentPadding,
        modifier =modifier ,
        tonalElevation = tonalElevation,
        content =  {
            content.invoke()
        }
    )


}

