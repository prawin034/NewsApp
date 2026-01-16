package com.skyapp.newsapp.domain.repository

import com.skyapp.newsapp.domain.model.Article


interface NewsDetailRepository  {
    suspend fun getNewsArticleById(id: Int): Result<Article>
}