package com.app.moviesapp.network.utils

import android.content.Context
import com.app.moviesapp.data.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            // TODO: Need to add authentication, instead of hardcoding
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYTlmZGUwNDI5YjQzOWQxMzk3NWZiOGRmZDUzMWJjMCIsIm5iZiI6MTcyMTIyMjAzOC4yNTcwNDYsInN1YiI6IjY2OGJlYTQzYjA3MTY0ZWQyMzk4YzkyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VU99XSZodv4_qedlnlt5aWC6UIXTWJ4ErnVOwN6LwdM")
            .build()

        val response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            if (!NetworkUtils.isOnline(context)) { throw NoNetworkException() } else { throw e }
        }
        return response
    }
}