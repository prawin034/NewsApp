package com.skyapp.newsapp.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topAppBar : @Composable () -> Unit,
    bottomAppBar : @Composable () -> Unit,
    floatingBtn : @Composable () -> Unit,
    snackBarHost : @Composable () -> Unit,
    floatingBtnPosition : FabPosition = FabPosition.End,
    containerColor : Color = Color.Transparent,
    contentColor : Color = Color.Black,
    content : @Composable (PaddingValues) -> Unit
){

    Scaffold(
        modifier = modifier,
        topBar = {
            topAppBar.invoke()
        },
        bottomBar = {
            bottomAppBar.invoke()
        },
        floatingActionButton = {
            floatingBtn.invoke()
        },
        snackbarHost = {
            snackBarHost.invoke()
        },
        floatingActionButtonPosition = floatingBtnPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        content = {
            Surface(
                color = Color.Transparent,
                modifier = Modifier.fillMaxSize()
                    .windowInsetsPadding(WindowInsets(0.dp)),

            ) {
                content.invoke(it)
            }
        }
    )



}