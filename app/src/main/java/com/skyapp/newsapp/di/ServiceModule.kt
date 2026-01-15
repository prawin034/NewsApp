package com.skyapp.newsapp.di

import com.skyapp.newsapp.data.remote.NewsDetailApiService
import com.skyapp.newsapp.data.remote.NewsFeedApiService
import com.skyapp.newsapp.data.remote.PreferencesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {




    @Provides
    @Singleton
    fun provideNewsFeedApiService(@Named("r1") retrofit : Retrofit)   : NewsFeedApiService {
        return retrofit.create(NewsFeedApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsDetailApiService(@Named("r1") retrofit : Retrofit) : NewsDetailApiService {
        return retrofit.create(NewsDetailApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePreferencesApiService(@Named("r1") retrofit : Retrofit) : PreferencesApiService {
        return retrofit.create(PreferencesApiService::class.java)

    }



}