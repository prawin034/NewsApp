package com.skyapp.newsapp.data.remote

import com.skyapp.newsapp.data.remote.dto.article.NewsArticleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsFeedApiService {



    @GET("v4/articles/")
    suspend fun getAllNewsArticles(
           @Query("limit") limit: Int,
           @Query("offset") offset: Int,
           @Query("title_contains") title: String? = null,
    ) : Response<NewsArticleResponseDto>




}