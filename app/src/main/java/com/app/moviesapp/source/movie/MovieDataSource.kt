package com.app.moviesapp.source.movie

import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.states.ResponseState

interface MovieDataSource {
    suspend fun getDiscoverMoviesList(): ResponseState<MoviesListResponse>

}