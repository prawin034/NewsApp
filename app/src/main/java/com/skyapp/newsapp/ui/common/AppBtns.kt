package com.skyapp.newsapp.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BackIconButton(
    color: Color = Color.Black,
    onClick: () -> Unit
) {

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = "App menu btn",
            tint = color,
            modifier = Modifier.size(23.dp)
        )
    }
}


@Composable
fun InfoBtn(
    color: Color = Color.Black,
    onClick: () -> Unit
) {

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            Icons.Default.Info,
            contentDescription = "App menu btn",
            tint = Color.Gray,
            modifier = Modifier.size(23.dp)
        )
    }
}


@Composable
fun MenuBarIconBtn(
    color: Color = Color.White,
    onClick: () -> Unit
) {

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Sort,
            contentDescription = "App menu btn",
            tint = color,
            modifier = Modifier.size(23.dp)
        )
    }
}

