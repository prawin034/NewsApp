package com.skyapp.newsapp.domain.repository

import com.skyapp.newsapp.domain.model.Article

interface PreferencesRepository  {
        suspend fun getAllNewsPrefs(limit: Int,offset: Int,search: String? = null) : List<Article>
}