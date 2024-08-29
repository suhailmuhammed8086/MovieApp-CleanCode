package com.app.moviesapp.source.movie

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MovieImagesResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.states.ResponseState

interface MovieDataSource {
    suspend fun getDiscoverMoviesList(genreIds: String?): ResponseState<MoviesListResponse>
    suspend fun getMovieDetails(movieId: Long): ResponseState<MovieDetailsResponse>
    suspend fun getMovieGenreList(): ResponseState<GenreListResponse>
    suspend fun getNowPlayingMovies(): ResponseState<MoviesListResponse>
    suspend fun getPopularMovies(): ResponseState<MoviesListResponse>
    suspend fun getTopRatedMovies(): ResponseState<MoviesListResponse>
    suspend fun getUpComingMovies(): ResponseState<MoviesListResponse>
    suspend fun getMovieImages(movieId: Long): ResponseState<MovieImagesResponse>
}