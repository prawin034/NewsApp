package com.skyapp.newsapp.data.remote


import com.skyapp.newsapp.data.remote.dto.article.NewsArticleItemDto
import com.skyapp.newsapp.data.remote.dto.article.NewsArticleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsDetailApiService {


    // Get Article by id
    @GET("v4/articles/{id}")
    suspend fun getArticle(@Path("id") id : Int) : Response<NewsArticleItemDto>
}