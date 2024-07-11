package com.app.moviesapp.di

import com.app.moviesapp.repository.MovieRepository
import com.app.moviesapp.repository.MovieRepositoryImpl
import com.app.moviesapp.source.MovieDataSource
import com.app.moviesapp.source.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindMovieDataSource(impl: MovieDataSourceImpl): MovieDataSource
}