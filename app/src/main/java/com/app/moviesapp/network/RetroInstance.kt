package com.app.moviesapp.network

import com.app.moviesapp.BuildConfig
import com.app.moviesapp.data.ValidationErrorException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroInstance {
    fun getInstance(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(loggingInterceptor)
                }
            }
            .addInterceptor {chain ->
                val request =chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYTlmZGUwNDI5YjQzOWQxMzk3NWZiOGRmZDUzMWJjMCIsIm5iZiI6MTcyMTIyMjAzOC4yNTcwNDYsInN1YiI6IjY2OGJlYTQzYjA3MTY0ZWQyMzk4YzkyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VU99XSZodv4_qedlnlt5aWC6UIXTWJ4ErnVOwN6LwdM")
                    .build()
                if (!isOnline()) {
                    throw ValidationErrorException(1000, "No Internet connection")
                }
                chain.proceed(request)
            }
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

    private fun isOnline(): Boolean{
        // TODO: Add online check logic here 
        return true
    }
}