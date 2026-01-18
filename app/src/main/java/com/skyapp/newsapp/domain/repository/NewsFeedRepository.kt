package com.skyapp.newsapp.domain.repository


import com.skyapp.newsapp.domain.model.Article


interface NewsFeedRepository  {

    suspend fun getAllNewsArticles(limit: Int,offset: Int,search: String? = null) : List<Article>
}




