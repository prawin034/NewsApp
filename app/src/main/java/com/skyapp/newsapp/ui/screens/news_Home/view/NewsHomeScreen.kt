package com.skyapp.newsapp.ui.screens.news_Home.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skyapp.newsapp.ui.common.AppBottomBar
import com.skyapp.newsapp.ui.common.AppBtn
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppLabelHeader
import com.skyapp.newsapp.ui.common.AppScaffold
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTopAppBar
import com.skyapp.newsapp.ui.common.ThemeToggleButton
import com.skyapp.newsapp.ui.navigation.NewsScreens
import com.skyapp.newsapp.ui.screens.news_Home.viewmodel.NewsHomeScreenViewModel
import com.skyapp.newsapp.ui.utils.mapIconName


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHomeScreen(
    navController: NavController,
    newsHomeScreenViewModel: NewsHomeScreenViewModel = hiltViewModel()
) {


    val context = LocalContext.current

    val getAllArticles by newsHomeScreenViewModel.newsUiState.collectAsStateWithLifecycle()

    val getNewsArticlesPaginated by newsHomeScreenViewModel.getNewsArticlesPaginated.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        Log.d("called","network call")
        if(getAllArticles.article.isEmpty()) {
           newsHomeScreenViewModel.getAllNewsArticles()
        }
    }

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )

    }


    val lazyListState  = rememberLazyListState()


    val bottomList = listOf("Home","Search","BookMark","Profile")

    val isDark  = remember { mutableStateOf(false) }

    AppScaffold(
        topAppBar = {
            AppTopAppBar(
                navigationIcon = {
                    AppCmnRow(
                        horizontalArrangement = Arrangement.Start,

                    ) {
                        AppLabelHeader(
                            name = "MallowTech"
                        )

                        AppSectionTextHeader(
                            text = "News",
                            letterSpacing = 3.sp,
                            fontSize = 19.5.sp,
                            textColor = contentColorFor(Color.Black),
                            fontFamily = FontFamily.Cursive,
                            modifier = Modifier.padding(start = 1.4.dp)

                        )
                    }
                },
                title = {},
                actions = {
                    ThemeToggleButton(
                        modifier = Modifier,
                        isDarkMode = isDark.value,
                        onToggle = {
                            isDark.value = !isDark.value
                        }
                    )

                    AppBtn(
                        icon = Icons.Default.MoreVert,
                        color = Color.Black
                    ) {

                    }
                }
            )
        },
        bottomAppBar = {
            if(getNewsArticlesPaginated.isLoading) {
                AppCmnRow(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Refreshing!", color = Color.Black, fontSize = 16.sp, modifier = Modifier.padding(2.dp))
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
        floatingBtn = {

        },
        snackBarHost = {

        },
        content = {
           Column(
               modifier = Modifier.fillMaxSize().padding(horizontal = 2.dp)
                   .background(color = Color(android.graphics.Color.parseColor("#F5F5F5")))
                   .padding(it),
               horizontalAlignment = Alignment.Start,
               verticalArrangement = Arrangement.SpaceBetween
           ) {
               LazyColumn(
                   state = lazyListState
               ) {
                   item {
                       NewsHomeTopPickupForYouSection(getAllArticles,navController)
                   }

                   item {
                       ExploreNewsSection(getAllArticles) {
                           navController.navigate(NewsScreens.NewsArticleScreen.route)
                       }
                   }


                   itemsIndexed(
                       getNewsArticlesPaginated.article,
                       key ={_, item -> item.id}
                   ) {index, item ->

                       NewsFeed(item){
                           navController.navigate(NewsScreens.NewsDetailScreen.passArgs(it))
                       }

                   }

               }


               LaunchedEffect(lazyListState) {
                  snapshotFlow { lazyListState.layoutInfo }
                      .collect {layoutInfo ->
                          val totalItemsCount = layoutInfo.totalItemsCount

                          val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?:0
                          if (lastVisibleItem >= totalItemsCount - 1) {
                              newsHomeScreenViewModel.getNewsArticlesPaginated()
                          }
                      }
               }

           }

        }

    )
}



