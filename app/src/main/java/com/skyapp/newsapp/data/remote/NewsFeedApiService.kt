package com.skyapp.newsapp.data.remote

import com.skyapp.newsapp.data.remote.dto.article.NewsArticleItemDto

import retrofit2.http.GET

interface NewsFeedApiService {



    @GET("/v4/articles/")
    suspend fun getAllNewsArticles() : retrofit2.Response<List<NewsArticleItemDto>>
}