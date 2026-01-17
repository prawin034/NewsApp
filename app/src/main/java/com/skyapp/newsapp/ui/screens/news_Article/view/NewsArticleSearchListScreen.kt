package com.skyapp.newsapp.ui.screens.news_Article.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.skyapp.newsapp.ui.common.AppBtn
import com.skyapp.newsapp.ui.common.AppScaffold
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTextBody2
import com.skyapp.newsapp.ui.common.AppTopAppBar
import com.skyapp.newsapp.ui.common.BackIconButton
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsArticleViewModel
import androidx.compose.runtime.getValue
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticleSearchListScreen(navController: NavController,newsArticleViewModel: NewsArticleViewModel) {


    val newUiState by newsArticleViewModel.newsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if(newUiState.article.isEmpty()) {
            newsArticleViewModel.getAllNewsArticles()
        }
    }

    AppScaffold(
        topAppBar = {
            AppTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    BackIconButton {
                        navController.popBackStack()
                    }
                },
                title = {},
                actions = {
                    Row {

                        AppBtn(
                            icon = Icons.Default.Favorite,
                            color = Color.Black
                        ) {
                        }

                        AppBtn(
                            icon = Icons.Default.Bookmark,
                            color = Color.Black
                        ) {

                        }
                    }
                }
            )
        },
        bottomAppBar = {},
        floatingBtn = {},
        snackBarHost = {},
        content = {
            Column(
                modifier = Modifier.fillMaxSize().padding(it)
                    .background(color = Color(android.graphics.Color.parseColor("#F5F5F5"))),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            )
            {

                AppSectionTextHeader(
                    text = "Discover More...",
                    fontSize = 24.5.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )

                AppTextBody2(
                    title = "News Related to space..",
                    fontSize = 12.sp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 10.dp)
                )

            }
        }
    )



}