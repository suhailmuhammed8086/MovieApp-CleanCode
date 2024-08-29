package com.app.moviesapp.network

import android.content.Context
import com.app.moviesapp.BuildConfig
import com.app.moviesapp.network.utils.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetroInstance {
    fun getInstance(context: Context): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) { addInterceptor(loggingInterceptor) }
            }
            .addInterceptor(NetworkInterceptor(context))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .baseUrl(BuildConfig.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <T> createService(retrofit: Retrofit, service: Class<T>): T {
        return retrofit.create(service)
    }

}