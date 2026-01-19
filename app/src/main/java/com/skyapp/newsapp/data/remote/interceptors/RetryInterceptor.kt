package com.skyapp.newsapp.data.remote.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetryInterceptor(
    private val maxRetry: Int = 3,
    private val retryDelayMillis: Long = 1000L // Optional delay between retries
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var attempt = 0
        var lastException: IOException? = null

        while (attempt < maxRetry) {
            try {
                return chain.proceed(request)
            } catch (e: IOException) {
                lastException = e
                attempt++

                Log.e("RetryInterceptor", "Request failed due to ${e.message}, attempt $attempt")
                Thread.sleep(retryDelayMillis)
            }
        }

        // If all retries failed, throw the last exception
        throw lastException ?: IOException("Unknown network error")
    }
}
