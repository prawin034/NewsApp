package com.skyapp.newsapp.di

import com.skyapp.newsapp.data.repository.NewsDetailRepositoryImpl
import com.skyapp.newsapp.data.repository.NewsFeedRepositoryImpl
import com.skyapp.newsapp.data.repository.PreferencesRepositoryImpl
import com.skyapp.newsapp.domain.repository.NewsDetailRepository
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import com.skyapp.newsapp.domain.repository.PreferencesRepository
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



    @Binds
    abstract fun bindNewsDetailRepository(
        newsDetailedImpl : NewsDetailRepositoryImpl
    ) : NewsDetailRepository


    @Binds
    abstract fun bindNewsPrefsRepository(prefsRepositoryImpl: PreferencesRepositoryImpl) : PreferencesRepository

}