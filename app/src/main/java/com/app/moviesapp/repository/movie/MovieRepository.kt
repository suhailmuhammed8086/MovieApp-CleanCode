package com.app.moviesapp.repository.movie

import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.states.ResponseState

interface MovieRepository  {

    suspend fun getDiscoverMoviesList(): ResponseState<MoviesListResponse>
}