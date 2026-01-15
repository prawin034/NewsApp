package com.skyapp.newsapp.domain.repository

import com.skyapp.newsapp.data.remote.dto.article.NewsArticleItemDto
import com.skyapp.newsapp.domain.model.Article


interface NewsFeedRepository  {

    suspend fun getAllNewsArticles() : List<Article>
}




