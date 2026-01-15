package com.skyapp.newsapp.domain.model





data class Article(
    val id: Int,
    val title: String,
    val authors: List<String>,
    val imageUrl: String,
    val summary: String,
    val publishedAt: String,
    val newsSite: String,
    val featured: Boolean
)