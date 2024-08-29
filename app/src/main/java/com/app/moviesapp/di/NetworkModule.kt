package com.app.moviesapp.di


import android.content.Context
import com.app.moviesapp.network.RetroInstance
import com.app.moviesapp.network.service.MoviesAndTvShowsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Singleton
    @Provides
    fun provideRetroInstance(@ApplicationContext context: Context): Retrofit {
        return RetroInstance.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MoviesAndTvShowsApiService {
        return RetroInstance.createService(retrofit, MoviesAndTvShowsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideName(): String {
        return "Asta"
    }

}