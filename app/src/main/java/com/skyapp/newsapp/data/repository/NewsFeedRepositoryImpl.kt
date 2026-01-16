package com.skyapp.newsapp.data.repository

import com.skyapp.newsapp.data.mapper.toDomain
import com.skyapp.newsapp.data.remote.NewsFeedApiService
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import javax.inject.Inject

class NewsFeedRepositoryImpl @Inject   constructor(
    private val api : NewsFeedApiService
) : NewsFeedRepository{

    override suspend fun getAllNewsArticles(
        limit: Int,offset: Int
    ): List<Article> {
        val response = api.getAllNewsArticles(limit,offset)
        if(!response.isSuccessful)
              throw Exception("Get All news Articles Failed, ${response.code()}")

        val body = response.body() ?: throw Exception("Empty Response")

        return   body.results.map { it.toDomain() }
    }
}