package com.app.moviesapp.di


import com.app.moviesapp.network.RetroInstance
import com.app.moviesapp.network.service.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Singleton
    @Provides
    fun provideRetroInstance(): Retrofit {
        return RetroInstance.getInstance()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MoviesApiService {
        return RetroInstance.createService(retrofit, MoviesApiService::class.java)
    }

}