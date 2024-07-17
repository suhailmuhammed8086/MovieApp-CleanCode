package com.app.moviesapp.source

import com.app.moviesapp.network.model.response.DiscoverMoviesListResponse
import com.app.moviesapp.states.ResponseState

interface MovieDataSource {
    suspend fun getDiscoverMoviesList(): ResponseState<DiscoverMoviesListResponse>

}