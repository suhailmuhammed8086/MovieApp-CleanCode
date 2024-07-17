package com.app.moviesapp.source

import com.app.moviesapp.network.model.response.DiscoverMoviesListResponse
import com.app.moviesapp.network.service.MoviesApiService
import com.app.moviesapp.states.ResponseState
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieApiService: MoviesApiService
) : MovieDataSource {
    override suspend fun getDiscoverMoviesList(): ResponseState<DiscoverMoviesListResponse> {
        val response = movieApiService.getDiscoverMovies()
        return ResponseState.Success(response)
    }
}