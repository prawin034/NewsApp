package com.skyapp.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skyapp.newsapp.ui.screens.news_Home.view.NewsHomeScreen

@Composable
fun NewsApp() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = NewsScreens.NewsHomeScreen.route
    ) {
        composable(
            route = NewsScreens.NewsHomeScreen.route
        ) {
            NewsHomeScreen(navController)
        }
    }
}