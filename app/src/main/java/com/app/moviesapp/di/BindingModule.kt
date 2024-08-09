package com.app.moviesapp.di

import com.app.moviesapp.repository.movie.MovieRepository
import com.app.moviesapp.repository.movie.MovieRepositoryImpl
import com.app.moviesapp.repository.tv.TvShowRepository
import com.app.moviesapp.repository.tv.TvShowRepositoryImpl
import com.app.moviesapp.source.movie.MovieDataSource
import com.app.moviesapp.source.movie.MovieDataSourceImpl
import com.app.moviesapp.source.tv_shows.TvShowDataSourceImpl
import com.app.moviesapp.source.tv_shows.TvShowsDataSource
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

    @Binds
    @Singleton
    abstract fun bindTvShowRepository(impl: TvShowRepositoryImpl): TvShowRepository

    @Binds
    @Singleton
    abstract fun bindTvShowDataSource(impl: TvShowDataSourceImpl): TvShowsDataSource
}