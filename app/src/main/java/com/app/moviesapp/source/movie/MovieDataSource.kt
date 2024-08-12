package com.app.moviesapp.source.movie

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.states.ResponseState

interface MovieDataSource {
    suspend fun getDiscoverMoviesList(): ResponseState<MoviesListResponse>
    suspend fun getMovieDetails(movieId: Int): ResponseState<MovieDetailsResponse>
    suspend fun getMovieGenreList(): ResponseState<GenreListResponse>

}