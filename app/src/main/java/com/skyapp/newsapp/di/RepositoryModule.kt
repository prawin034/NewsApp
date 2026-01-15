package com.skyapp.newsapp.di

import com.skyapp.newsapp.data.repository.NewsFeedRepositoryImpl
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class  RepositoryModule {


    @Binds
    abstract fun bindNewsFeedRepository(
        newsFeedRepositoryImpl: NewsFeedRepositoryImpl
    ) : NewsFeedRepository
}