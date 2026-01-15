package com.skyapp.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NewsApp() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = NewsScreens.NewsHomeScreen.route
    ) {



    }
}