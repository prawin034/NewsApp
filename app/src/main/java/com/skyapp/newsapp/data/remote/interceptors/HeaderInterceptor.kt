package com.skyapp.newsapp.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() :  Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val newRequest = request.newBuilder()

        newRequest.addHeader("Content-Type","application/json")
        newRequest.addHeader("Accept","application/json")

        return chain.proceed(newRequest.build())
    }
}