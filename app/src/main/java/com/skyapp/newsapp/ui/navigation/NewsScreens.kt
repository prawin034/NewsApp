package com.skyapp.newsapp.ui.navigation




sealed class NewsScreens(val route : String) {


    data object NewsHomeScreen : NewsScreens("NewsHomeScreen")
    data object NewsArticleScreen : NewsScreens("NewsArticleScreen")
    data object NewsDetailScreen : NewsScreens("NewsDetailScreen/{id}") {
        fun passArgs(id: Int) : String {
            return "NewsDetailScreen/$id"
        }
    }

}