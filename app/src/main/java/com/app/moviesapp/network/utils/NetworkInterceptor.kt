package com.app.moviesapp.network.utils

import android.content.Context
import com.app.moviesapp.BuildConfig
import com.app.moviesapp.data.NoNetworkException
import com.app.moviesapp.network.utils.NetworkUtils.API_KEY
import com.app.moviesapp.utils.log
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.ApiKey)
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        val response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            if (!NetworkUtils.isOnline(context)) { throw NoNetworkException() } else { throw e }
        }
        return response
    }
}