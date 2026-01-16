package com.skyapp.newsapp.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    title : @Composable () -> Unit,
    navigationIcon : @Composable () -> Unit,
    actions : @Composable () -> Unit,
    modifier: Modifier = Modifier,
    colors : TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Transparent,
        navigationIconContentColor = contentColorFor(Color.Transparent)
    ),
    expandedHeight : Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = {
            title.invoke()
        },
        navigationIcon = {
            navigationIcon.invoke()
        },
        actions = {
            actions.invoke()
        },
        expandedHeight = expandedHeight ,
        colors = colors,
        modifier = modifier,
        scrollBehavior = scrollBehavior

    )

}