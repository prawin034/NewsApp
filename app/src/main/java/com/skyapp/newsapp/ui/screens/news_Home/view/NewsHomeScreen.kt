package com.skyapp.newsapp.ui.screens.news_Home.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
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
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppLabelHeader
import com.skyapp.newsapp.ui.common.AppScaffold
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTopAppBar
import com.skyapp.newsapp.ui.screens.news_Home.viewmodel.NewsHomeScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHomeScreen(
    navController: NavController,
    newsHomeScreenViewModel: NewsHomeScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        Log.d("called","network call")
        newsHomeScreenViewModel.getAllNewsArticles()
    }
    val context = LocalContext.current

    val getAllArticles by newsHomeScreenViewModel.newsUiState.collectAsStateWithLifecycle()



    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )

    }


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
//                    AppNotificationBtn {
//                    }
//
//                    MoreBtn(
//                        icon = Icons.Default.MoreVert,
//                        color = Color.White
//                    ) { }

                }
            )
        },
        bottomAppBar = {},
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


               LazyColumn {
                   item {
                       NewsHomeTopPickupForYouSection(getAllArticles)
                   }

                   item {
                       ExploreNewsSection(getAllArticles)
                   }


                   itemsIndexed(
                       getAllArticles.article,
                       key ={_, item -> item.id}
                   ) {index, item ->

                       NewsFeed(item)

                   }


               }




           }

        }

    )
}



