package com.app.moviesapp.repository

import com.app.moviesapp.network.model.response.DiscoverMoviesListResponse
import com.app.moviesapp.states.ResponseState

interface MovieRepository  {

    suspend fun getDiscoverMoviesList(): ResponseState<DiscoverMoviesListResponse>
}