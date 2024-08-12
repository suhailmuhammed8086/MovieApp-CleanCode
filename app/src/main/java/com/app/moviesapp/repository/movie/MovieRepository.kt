package com.app.moviesapp.repository.movie

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.states.ResponseState

interface MovieRepository  {

    suspend fun getDiscoverMoviesList(): ResponseState<MoviesListResponse>
    suspend fun getDiscoverMovieDetails(movieId:Int): ResponseState<MovieDetailsResponse>
    suspend fun getMovieGenreList(): ResponseState<GenreListResponse>
}