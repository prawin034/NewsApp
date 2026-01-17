package com.skyapp.newsapp.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun ThemeToggleButton(
    isDarkMode: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (isDarkMode) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "Rotate anim"
    )

    val icon = if (isDarkMode) {
        Icons.Outlined.DarkMode   //
    } else {
        Icons.Outlined.LightMode  //
    }

    IconButton(
        onClick = onToggle,
        modifier = modifier
            .size(48.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Toggle theme",
            modifier = Modifier.rotate(rotation),
            tint = if (isDarkMode)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.tertiary
        )
    }
}
