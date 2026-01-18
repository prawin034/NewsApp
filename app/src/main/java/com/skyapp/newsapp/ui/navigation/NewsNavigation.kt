package com.skyapp.newsapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skyapp.newsapp.ui.screens.news_Article.view.NewsArticleSearchListScreen
import com.skyapp.newsapp.ui.screens.news_Detail.view.NewsDetailedScreen
import com.skyapp.newsapp.ui.screens.news_Home.view.NewsHomeScreen
import com.skyapp.newsapp.ui.screens.preference.view.PreferenceScreen
import com.skyapp.newsapp.ui.utils.newsAppFadeIn
import com.skyapp.newsapp.ui.utils.newsAppFadeOut

@Composable
fun NewsApp() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = NewsScreens.NewsPreferenceScreen.route
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
            ),
            enterTransition = {
                newsAppFadeIn() + slideIntoContainer(
                    animationSpec = tween(500, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                newsAppFadeOut() + slideOutOfContainer(
                    animationSpec = tween(500, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) { backStackEntry ->
            val id  = backStackEntry.arguments?.getInt("id") ?:0
            NewsDetailedScreen(navController,id)
        }

        composable(
            route = NewsScreens.NewsArticleScreen.route,
            enterTransition = {
                newsAppFadeIn() + slideIntoContainer(
                    animationSpec = tween(500, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                newsAppFadeOut() + slideOutOfContainer(
                    animationSpec = tween(500, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ){
            NewsArticleSearchListScreen(navController)
        }

        composable(
            route = NewsScreens.NewsPreferenceScreen.route
        ) {
            PreferenceScreen(navController)
        }
    }
}