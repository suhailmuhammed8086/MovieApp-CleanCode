package com.app.moviesapp.source.movie

import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.network.service.MoviesAndTvShowsApiService
import com.app.moviesapp.states.ResponseState
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieApiService: MoviesAndTvShowsApiService
) : MovieDataSource {
    override suspend fun getDiscoverMoviesList(): ResponseState<MoviesListResponse> {
        val response = movieApiService.getDiscoverMovies()
        return ResponseState.Success(response)
    }
}