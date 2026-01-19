package com.skyapp.newsapp.data.mapper

import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.data.remote.dto.article.NewsArticleItemDto





fun NewsArticleItemDto.toDomain() = Article(
    id = id ?:0,
    title = title.orEmpty(),
    authors = authors.mapNotNull { it.name },
    imageUrl = imageUrl.orEmpty(),
    summary = summary.orEmpty(),
    publishedAt = publishedAt.orEmpty(),
    newsSite = newsSite.orEmpty(),
    featured = featured == true,
    url = url.orEmpty()
)



