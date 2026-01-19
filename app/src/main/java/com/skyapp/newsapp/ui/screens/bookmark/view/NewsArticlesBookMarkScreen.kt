package com.skyapp.newsapp.ui.screens.bookmark.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppScaffold
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTopAppBar
import com.skyapp.newsapp.ui.common.BackIconButton
import com.skyapp.newsapp.ui.screens.bookmark.viewmodel.BookMarkViewModel
import com.skyapp.newsapp.ui.utils.NewsAppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticlesBookMarkScreen(navController: NavController,bookMarkViewModel: BookMarkViewModel = hiltViewModel()) {

    val myPrefsData by bookMarkViewModel.userPrefsData.collectAsStateWithLifecycle()


    val systemUiController = rememberSystemUiController()
    val isDarkMode = myPrefsData.isDarkModeEnabled
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (isDarkMode) Color.Black else Color(NewsAppConstants.bgColor),
            darkIcons = !isDarkMode
        )
    }
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
        },
        floatingBtn = {},
        snackBarHost = {},
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                AppSectionTextHeader(text = "Book mark Not implemented yet!", fontSize = 23.sp, textColor = Color.Black)

            }

        })
}