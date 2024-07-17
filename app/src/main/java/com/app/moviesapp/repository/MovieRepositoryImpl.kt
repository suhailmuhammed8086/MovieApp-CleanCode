package com.app.moviesapp.repository

import com.app.moviesapp.network.model.response.DiscoverMoviesListResponse
import com.app.moviesapp.source.MovieDataSource
import com.app.moviesapp.states.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
) : MovieRepository {
    override suspend fun getDiscoverMoviesList(): ResponseState<DiscoverMoviesListResponse> {
        return withContext(Dispatchers.Main) {
            return@withContext movieDataSource.getDiscoverMoviesList()
        }
    }
}