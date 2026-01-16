package com.skyapp.newsapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skyapp.newsapp.ui.screens.news_Detail.view.NewsDetailedScreen
import com.skyapp.newsapp.ui.screens.news_Home.view.NewsHomeScreen

@RequiresApi(Build.VERSION_CODES.O)
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

        composable(
            route = NewsScreens.NewsDetailScreen.route,
            arguments = listOf(
                navArgument("id") {type = NavType.IntType}
            )
        ) { backStackEntry ->
            val id  = backStackEntry.arguments?.getInt("id") ?:0
            NewsDetailedScreen(navController,id)
        }

    }
}