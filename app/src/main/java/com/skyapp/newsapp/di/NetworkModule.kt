package com.skyapp.newsapp.di

import com.skyapp.newsapp.data.remote.interceptors.HeaderInterceptor
import com.skyapp.newsapp.data.remote.interceptors.RetryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    //provide okhttp
    @Provides
    @Singleton
    @Named("okhttp")
    fun provideOkHttpClient() : OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(RetryInterceptor())
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build()
    }



    //profit retrofit

    @Provides
    @Singleton
    @Named("r1")
    fun provideRetrofit(@Named("okhttp") okhttpClient: OkHttpClient) : Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spaceflightnewsapi.net")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()

        return  retrofit
    }

}