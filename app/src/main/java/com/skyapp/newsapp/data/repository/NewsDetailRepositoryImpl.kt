package com.skyapp.newsapp.data.repository

import com.skyapp.newsapp.data.mapper.toDomain
import com.skyapp.newsapp.data.remote.NewsDetailApiService
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.domain.repository.NewsDetailRepository
import javax.inject.Inject

class NewsDetailRepositoryImpl @Inject constructor(
    private val apiService: NewsDetailApiService
) : NewsDetailRepository {

    override suspend fun getNewsArticleById(id: Int): Result<Article> {
       return  try {
           val response = apiService.getArticle(id)
           if(response.isSuccessful) {
               val responseBody = response.body()
               if(responseBody != null) {
                   Result.success(responseBody.toDomain())
               }
               else{
                   Result.failure(Exception("Error : Response body is null ${response.code()}"))
               }
           }
           else {
               Result.failure(
                   Exception("Request failed code=${response.code()}")
               )
           }
       }catch (e: Exception) {
           Result.failure(e)
       }
    }
}