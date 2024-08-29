package com.app.moviesapp.repository.movie

import com.app.moviesapp.network.model.request.DiscoverMoviesRequest
import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MovieImagesResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.source.movie.MovieDataSource
import com.app.moviesapp.states.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
) : MovieRepository {
    override suspend fun getDiscoverMoviesList(discoverMoviesRequest: DiscoverMoviesRequest): ResponseState<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            val genreIds = if (discoverMoviesRequest.genreIds.isNotEmpty()) {
                var result = ""
                discoverMoviesRequest.genreIds.forEach { genreId ->
                    result += genreId.toString().plus("|")
                }
                // removing last pipe
                result.dropLast(1)
            } else {
                null
            }

            return@withContext movieDataSource.getDiscoverMoviesList(
                genreIds = genreIds
            )
        }
    }
    override suspend fun getMovieDetails(movieId: Long): ResponseState<MovieDetailsResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.getMovieDetails(movieId)
        }
    }
    override suspend fun getMovieGenreList(): ResponseState<GenreListResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.getMovieGenreList()
        }
    }

    override suspend fun getNowPlayingMovies(): ResponseState<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.getNowPlayingMovies()
        }
    }

    override suspend fun getPopularMovies(): ResponseState<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.getPopularMovies()
        }
    }

    override suspend fun getTopRatedMovies(): ResponseState<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.getTopRatedMovies()
        }
    }

    override suspend fun getUpComingMovies(): ResponseState<MoviesListResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.getUpComingMovies()
        }
    }

    override suspend fun getMovieImages(movieId: Long): ResponseState<MovieImagesResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext movieDataSource.getMovieImages(movieId)
        }
    }
}