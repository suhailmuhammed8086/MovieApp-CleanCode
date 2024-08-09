package com.app.moviesapp.repository.movie

import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.source.movie.MovieDataSource
import com.app.moviesapp.states.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
) : MovieRepository {
    override suspend fun getDiscoverMoviesList(): ResponseState<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.getDiscoverMoviesList()
        }
    }
}