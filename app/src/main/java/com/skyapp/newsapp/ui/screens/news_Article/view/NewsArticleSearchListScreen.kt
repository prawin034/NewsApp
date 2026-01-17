package com.skyapp.newsapp.ui.screens.news_Article.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skyapp.newsapp.ui.common.AppBtn
import com.skyapp.newsapp.ui.common.AppLoader
import com.skyapp.newsapp.ui.common.AppScaffold
import com.skyapp.newsapp.ui.common.AppSearchBar
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTextBody2
import com.skyapp.newsapp.ui.common.AppTopAppBar
import com.skyapp.newsapp.ui.common.BackIconButton
import com.skyapp.newsapp.ui.common.NoDataFound
import com.skyapp.newsapp.ui.navigation.NewsScreens
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsArticleViewModel
import com.skyapp.newsapp.ui.screens.news_Home.view.NewsFeed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticleSearchListScreen(navController: NavController,newsArticleViewModel: NewsArticleViewModel = hiltViewModel()) {


    val newUiState by newsArticleViewModel.newsUiState.collectAsStateWithLifecycle()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = true
        )

    }

    LaunchedEffect(Unit) {
        if(newUiState.article.isEmpty()) {
            newsArticleViewModel.getAllNewsArticles()
        }
    }


    val searchQuery by newsArticleViewModel.searchQuery.collectAsStateWithLifecycle()
    val searchList by newsArticleViewModel.searchList.collectAsStateWithLifecycle()

    AppScaffold(
        topAppBar = {
            AppTopAppBar(
                modifier = Modifier.shadow(4.dp, ambientColor = Color.Black, spotColor = Color.Black),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(android.graphics.Color.parseColor("#F5F5F5"))
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
                },

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
                verticalArrangement = Arrangement.spacedBy(20.dp)
            )
            {

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            item {
                                AppSectionTextHeader(
                                    text = "Discover More...",
                                    fontSize = 34.5.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )

                                AppTextBody2(
                                    title = "News Related to space..".lowercase(),
                                    fontSize = 14.sp,
                                    color = Color.LightGray,
                                    modifier = Modifier.padding(start = 10.dp)
                                )


                                AppSearchBar(
                                    query = searchQuery,
                                    onQueryChange = {
                                        newsArticleViewModel.onChangeSearchQuery(it)
                                    },
                                    onSearch = {
                                        newsArticleViewModel.addSearchList(it)
                                        newsArticleViewModel.getAllNewsArticles(
                                            search = searchQuery
                                        )
                                    },
                                    searchResults =searchList ,
                                    onResultClick = {

                                    },
                                    trailingIcon = {
//                                AppBtn(
//                                    icon = Icons.Default.FilterList,
//                                    color = Color.White
//                                ) {
//
//                                }
                                    },
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                )
                            }
                            when{
                                newUiState.isLoading -> {
                                    item {
                                        AppLoader()
                                    }
                                }
                                !newUiState.isLoading && newUiState.article.isEmpty() && searchQuery.isNotEmpty() -> {
                                    item {
                                        NoDataFound()
                                    }
                                }

                                !newUiState.isLoading && newUiState.article.isNotEmpty() -> {
                                    itemsIndexed(newUiState.article, key =  {_,item -> item.id }) {index, item ->
                                        NewsFeed(item) {
                                            navController.navigate(
                                                NewsScreens.NewsDetailScreen.passArgs(
                                                    it
                                                )
                                            )
                                        }
                                    }
                                }
                            }

                        }
            }
        }
    )
}