package com.skyapp.newsapp.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    thenModifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(
        topStart = 18.dp,
        topEnd = 18.dp,
        bottomStart = 18.dp,
        bottomEnd = 18.dp
    ),
    colors : CardColors = CardDefaults.cardColors(

    ),
    content : @Composable () -> Unit
) {

    Card(
        modifier = modifier.fillMaxWidth()
               .shadow(3.dp, shape = shape, ambientColor = Color.Black)
            .then(thenModifier),
        shape = shape,
        colors = colors

    ) {
        content.invoke()

    }
}