package com.skyapp.newsapp.ui.screens.news_Detail.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppLabelHeader
import com.skyapp.newsapp.ui.common.AppScaffold
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTopAppBar
import com.skyapp.newsapp.ui.common.BackIconButton
import com.skyapp.newsapp.ui.screens.news_Detail.viewmodel.NewsDetailedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.room.util.TableInfo
import com.skyapp.newsapp.ui.common.AppBtn
import com.skyapp.newsapp.ui.common.AppCardHeader
import com.skyapp.newsapp.ui.common.AppTextBody1
import com.skyapp.newsapp.ui.common.AppTextBody2
import com.skyapp.newsapp.ui.utils.parseValidDateString
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsDetailedScreen(navController: NavController, id: Int, newsDetailedViewModel: NewsDetailedViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        newsDetailedViewModel.getNewsDetail(id)
    }

    val articleDetails by  newsDetailedViewModel.getNewsDetails.collectAsStateWithLifecycle()
    val successMSg by newsDetailedViewModel.successMsg.collectAsStateWithLifecycle()
    val failureMsg by newsDetailedViewModel.failureMsg.collectAsStateWithLifecycle()
    val isLoading by newsDetailedViewModel.isLoading.collectAsStateWithLifecycle()

    val context = LocalContext.current


//    val formattedDate = remember(articleDetails?.publishedAt) {
//        parseValidDateString(articleDetails?.publishedAt)
//    }
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
                            icon = Icons.Default.Headphones
                        ) {

                        }

                        AppBtn(
                            icon = Icons.Default.UploadFile
                        ) {
                        }

                        AppBtn(
                            icon = Icons.Default.MoreHoriz
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
                modifier = Modifier.fillMaxSize().padding(horizontal = 0.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#F5F5F5"))),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            )
            {
                //1. Detailed image--
                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                        AsyncImage(
                            model = articleDetails?.imageUrl ?:"",
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.Start
                        ) {

                            // Title
                            AppCardHeader(
                                title = articleDetails?.newsSite.plus("."),
                                maxLines = 2,
                                fontSize = 10.sp,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth()
                            )
                            AppTextBody1(
                                title = articleDetails?.title ?:"",
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White,
                                fontSize = 16.5.sp
                            )


                            // Outlined Button at end
                            OutlinedButton(
                                onClick = { /* TODO */ },
                                border = BorderStroke(1.dp, Color.White),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.White
                                ),
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(55.dp)
                            ) {
                                AppCmnRow {
                                   Text("Read more ")
                                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "", tint = Color.White)
                                }
                            }

                        }





                    }

                }

                //2 . Detailed Description
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp).verticalScroll(
                        rememberScrollState()
                    ),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {

                    AppCmnRow {

                       Column {
                           AppCardHeader(
                               title = articleDetails?.newsSite ?:""
                           )

                           AppTextBody2(
                               title = articleDetails?.publishedAt ?:""
                           )
                       }



                        AsyncImage(
                            model = articleDetails?.imageUrl ?:"",
                            contentDescription = null,
                            modifier = Modifier.size(55.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop,

                        )





                    }


                    repeat(5) {
                        AppTextBody1(
                            title =  articleDetails?.summary?.capitalize(Locale.ROOT) ?:"",
                            fontSize = 14.5.sp,
                            modifier = Modifier.padding(4.dp)
                        )
                    }



                }






            }
        }
    )












}