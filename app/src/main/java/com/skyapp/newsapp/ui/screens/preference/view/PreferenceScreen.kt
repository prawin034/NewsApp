package com.skyapp.newsapp.ui.screens.preference.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skyapp.newsapp.ui.common.AppBtn
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppLabelHeader
import com.skyapp.newsapp.ui.common.AppScaffold
import com.skyapp.newsapp.ui.common.AppSearchBar
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTopAppBar
import com.skyapp.newsapp.ui.common.ThemeToggleButton
import com.skyapp.newsapp.ui.screens.preference.viewmodel.PreferenceViewModel
import com.skyapp.newsapp.ui.utils.NewsAppConstants
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.skyapp.newsapp.ui.common.AppBottomBar
import com.skyapp.newsapp.ui.common.AppCard
import com.skyapp.newsapp.ui.common.AppLoader
import com.skyapp.newsapp.ui.common.AppTextBody2
import com.skyapp.newsapp.ui.common.NoDataFound
import com.skyapp.newsapp.ui.navigation.NewsScreens
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen(
    navController: NavController,prefsViewModel: PreferenceViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color(NewsAppConstants.topAppBarBgColor),
            darkIcons = true
        )

    }

    val prefsNewsAll by prefsViewModel.prefsUiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        if(prefsNewsAll.article.isEmpty()){
            prefsViewModel.getNewsPrefs(20,0)
        }
    }

    val myPrefs by prefsViewModel.myPrefs.collectAsStateWithLifecycle()
    val uniquePrefs  =  prefsNewsAll.article.distinctBy { it.newsSite }
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
                title = {
                },
                actions = {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Skip", fontSize = 14.2.sp, color = contentColorFor(Color(NewsAppConstants.topAppBarBgColor)))
                        AppBtn(
                            Icons.Default.SkipNext,
                            color = contentColorFor(Color(NewsAppConstants.topAppBarBgColor))
                        ) {
                            navController.navigate(NewsScreens.NewsHomeScreen.route){
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        },
        bottomAppBar = {
            AppBottomBar(
                onClick = {
                    navController.navigate(NewsScreens.NewsHomeScreen.route){
                        launchSingleTop = true
                    }
                },
                content = {
                    AppSectionTextHeader(
                        text = "Save Preference",
                        textColor = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center

                    )
                }
            )
        },
        snackBarHost = {},
        floatingBtn = {},
        content =  {
            Column(
                modifier = Modifier.fillMaxSize().padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

               // Spacer(modifier = Modifier.height(10.dp))
                AppCmnRow(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp).padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    AppSectionTextHeader(
                        text = "Choose your preferences",
                        fontSize = 19.sp,
                        textColor =  contentColorFor(Color(NewsAppConstants.topAppBarBgColor)),
                        textAlign = TextAlign.Start
                    )
                }
                LazyColumn {
                    when {
                        prefsNewsAll.isLoading -> {
                            item {
                                AppLoader()
                            }
                        }
                        prefsNewsAll.article.isNotEmpty() && uniquePrefs.isNotEmpty() -> {
                            itemsIndexed(uniquePrefs) {index, item ->
                                AppCard(
                                    modifier = Modifier.padding(10.dp)
                                        .clickable {
                                            if(myPrefs.contains(item.newsSite))
                                                prefsViewModel.removeMyPrefs(item.newsSite)
                                            else prefsViewModel.addMyPrefs(item.newsSite)
                                        },
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (myPrefs.contains(item.newsSite)) Color(
                                            NewsAppConstants.primary) else Color.White,
                                        contentColor = if (myPrefs.contains(item.newsSite)) Color.Black else Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    AppCmnRow(
                                        modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        AppTextBody2(item.newsSite, fontSize = 13.sp, color =  if (myPrefs.contains(item.newsSite)) Color.White else Color.Black)
                                    }
                                }
                                Spacer(modifier = Modifier.height(5.dp))



                            }
                        }
                        else -> {
                            item {
                                NoDataFound("Please retry again! error Loading!")
                            }
                        }
                    }



                }











            }
        }
    )





}

