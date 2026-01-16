package com.skyapp.newsapp.ui.screens.news_Detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
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

                }
            )
        },
        bottomAppBar = {},
        floatingBtn = {},
        snackBarHost = {},
        content = {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 2.dp)
                    .background(color = Color(android.graphics.Color.parseColor("#F5F5F5")))
                    .padding(it),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            )
            {
                //1. Detailed image--
                AsyncImage(
                    model = articleDetails?.imageUrl ?:"",
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)
                )




                //2 . Detailed Description






            }
        }
    )












}