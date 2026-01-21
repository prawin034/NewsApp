package com.skyapp.newsapp.ui.screens.news_Article.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skyapp.newsapp.ui.common.AppBtn
import com.skyapp.newsapp.ui.common.AppCmnRow
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
import com.skyapp.newsapp.ui.utils.NewsAppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticleSearchListScreen(navController: NavController,newsArticleViewModel: NewsArticleViewModel = hiltViewModel()) {


   // val newUiState by newsArticleViewModel.newsUiState.collectAsStateWithLifecycle()

    val getNewsArticlesPaginated by newsArticleViewModel.getNewsArticlesPaginated.collectAsStateWithLifecycle()


    val myPrefsData by newsArticleViewModel.userPrefsData.collectAsStateWithLifecycle()


    val systemUiController = rememberSystemUiController()
    val isDarkMode = myPrefsData.isDarkModeEnabled
    val lazyListState  = rememberLazyListState()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (isDarkMode) Color.Black else Color(NewsAppConstants.bgColor),
            darkIcons = !isDarkMode
        )
    }
    LaunchedEffect(Unit) {
        if(getNewsArticlesPaginated.article.isEmpty()) {
            newsArticleViewModel.getNewsArticlesPaginated()
        }
    }


    val searchQuery by newsArticleViewModel.searchQuery.collectAsStateWithLifecycle()
    val searchList by newsArticleViewModel.searchList.collectAsStateWithLifecycle()
    val context = LocalContext.current


    val isPullToRefreshTriggered by newsArticleViewModel.isPullToRefreshTriggered.collectAsStateWithLifecycle()



    AppScaffold(
        isDarkModeEnabled = isDarkMode,
        topAppBar = {
            AppTopAppBar(
                isDarkModeEnabled = isDarkMode,
                modifier = Modifier.shadow(4.dp, ambientColor = Color.Black, spotColor = Color.Black),
                navigationIcon = {
                    BackIconButton {
                        navController.popBackStack()
                    }
                },
                title = {},
                actions = {

                },

            )
        },
        bottomAppBar = {
            if(getNewsArticlesPaginated.isLoading) {
                AppCmnRow(
                    modifier = Modifier.fillMaxWidth().padding(20.dp).padding(bottom = 40.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Refreshing!", color = if(myPrefsData.isDarkModeEnabled) Color.White else Color(0xFF000000), fontSize = 16.sp, modifier = Modifier.padding(2.dp),)
                    CircularProgressIndicator(
                        color = Color.Green,
                        strokeWidth = 5.dp
                    )
                }
            }
            if(getNewsArticlesPaginated.error !=null){
                Toast.makeText(context,getNewsArticlesPaginated.error, Toast.LENGTH_SHORT).show()
            }
        },
        floatingBtn = {},
        snackBarHost = {},
        content = {

            @OptIn(ExperimentalMaterial3Api::class)
            PullToRefreshBox(
                isRefreshing = isPullToRefreshTriggered,
                onRefresh = {
                    newsArticleViewModel.pullToRefresh(true)
                    newsArticleViewModel.resetPagination()
                    newsArticleViewModel.getAllNewsArticles()
                    newsArticleViewModel.pullToRefresh(false)
                    newsArticleViewModel.clearSearchQuery()
                },
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(it)
                    ,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                )
                {

                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        item {
                            AppSectionTextHeader(
                                text = "Discover More...",
                                fontSize = 34.5.sp,
                                textColor = if(isDarkMode) Color.White else Color(0xFF000000),
                                modifier = Modifier.padding(start = 10.dp)
                            )

                            AppTextBody2(
                                title = "News Related to space..".lowercase(),
                                fontSize = 14.sp,
                                color = if(isDarkMode) Color.White else Color.LightGray,
                                modifier = Modifier.padding(start = 10.dp)
                            )


                            AppSearchBar(
                                query = searchQuery,
                                onQueryChange = {
                                    newsArticleViewModel.onChangeSearchQuery(it)
                                },
                                onSearch = {
                                    //newsArticleViewModel.addSearchList(it)
                                    newsArticleViewModel.getAllNewsArticles(
                                        search = searchQuery
                                    )
                                    newsArticleViewModel.resetPagination()
                                    newsArticleViewModel.clearSearchQuery()
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
                            getNewsArticlesPaginated.isLoading && getNewsArticlesPaginated.article.isEmpty() -> {
                                item {
                                    AppLoader()
                                }
                            }
                            !getNewsArticlesPaginated.isLoading && getNewsArticlesPaginated.article.isEmpty() && searchQuery.isNotEmpty() -> {
                                item {
                                    NoDataFound(
                                        isDarMode = isDarkMode
                                    )
                                }
                            }
                        }


                        itemsIndexed(getNewsArticlesPaginated.article, key =  {_,item -> "${item.id}_${item.publishedAt}"
                        }) {index, item ->
                            NewsFeed(item) {
                                navController.navigate(
                                    NewsScreens.NewsDetailScreen.passArgs(
                                        item.id,
                                    )
                                )
                            }
                        }


                    }

                    LaunchedEffect(lazyListState) {
                        snapshotFlow { lazyListState.layoutInfo }
                            .collect {layoutInfo ->
                                val totalItemsCount = layoutInfo.totalItemsCount

                                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?:0
                                if (lastVisibleItem >= totalItemsCount - 1) {
                                    newsArticleViewModel.getNewsArticlesPaginated()
                                }
                            }
                    }
                }
            }


        }
    )
}