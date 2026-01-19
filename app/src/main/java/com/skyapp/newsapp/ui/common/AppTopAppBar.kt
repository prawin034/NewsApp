package com.skyapp.newsapp.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skyapp.newsapp.ui.navigation.NewsApp
import com.skyapp.newsapp.ui.utils.NewsAppConstants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    title : @Composable () -> Unit,
    navigationIcon : @Composable () -> Unit,
    actions : @Composable () -> Unit,
    modifier: Modifier = Modifier.shadow(2.dp, ambientColor = Color.Black),
    colors : TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color(NewsAppConstants.topAppBarBgColor),
    navigationIconContentColor = contentColorFor(Color(NewsAppConstants.topAppBarBgColor))
    ),
    expandedHeight : Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    isDarkModeEnabled : Boolean = false,
) {

  val colors =   if(isDarkModeEnabled)  {

      TopAppBarDefaults.topAppBarColors(
          containerColor = Color.Black,
          navigationIconContentColor = contentColorFor(Color.Black)
      )
  }
    else {
      TopAppBarDefaults.topAppBarColors(
          containerColor = Color.Transparent,
          navigationIconContentColor = contentColorFor(Color(NewsAppConstants.topAppBarBgColor))
      )

  }

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