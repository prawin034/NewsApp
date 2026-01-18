package com.skyapp.newsapp.data.repository

import com.skyapp.newsapp.data.mapper.toDomain
import com.skyapp.newsapp.data.remote.PreferencesApiService
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.domain.repository.PreferencesRepository
import retrofit2.HttpException
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val apiService: PreferencesApiService
) : PreferencesRepository {

    override suspend fun getAllNewsPrefs(
        limit: Int,
        offset: Int,
        search: String?
    ): List<Article> {

        val response = apiService.getNewsPreferences(limit, offset, search)

        if (!response.isSuccessful) {
            throw HttpException(response)
        }

        val body = response.body()
            ?: throw IllegalStateException("Empty response body")

        return body.results.map { it.toDomain() }
    }
}