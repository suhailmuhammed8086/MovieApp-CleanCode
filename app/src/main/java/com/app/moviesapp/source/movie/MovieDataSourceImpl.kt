package com.app.moviesapp.source.movie

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MovieImagesResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.network.service.MoviesAndTvShowsApiService
import com.app.moviesapp.states.ResponseState
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieApiService: MoviesAndTvShowsApiService
) : MovieDataSource {
    override suspend fun getDiscoverMoviesList(genreIds: String?): ResponseState<MoviesListResponse> {
        val response = movieApiService.getDiscoverMovies(genreIds = genreIds)
        return ResponseState.Success(response)
    }

    override suspend fun getMovieDetails(movieId: Long): ResponseState<MovieDetailsResponse> {
        val response = movieApiService.getMovieDetails(movieId)
        return ResponseState.Success(response)
    }

    override suspend fun getMovieGenreList(): ResponseState<GenreListResponse> {
        val response = movieApiService.getMovieGenreList()
        return ResponseState.Success(response)
    }

    override suspend fun getNowPlayingMovies(): ResponseState<MoviesListResponse> {
        val response = movieApiService.getNowPlayingMovies()
        return ResponseState.Success(response)
    }

    override suspend fun getPopularMovies(): ResponseState<MoviesListResponse> {
        val response = movieApiService.getPopularMovies()
        return ResponseState.Success(response)
    }

    override suspend fun getTopRatedMovies(): ResponseState<MoviesListResponse> {
        val response = movieApiService.getTopRatedMovies()
        return ResponseState.Success(response)
    }

    override suspend fun getUpComingMovies(): ResponseState<MoviesListResponse> {
        val response = movieApiService.getUpComingMovies()
        return ResponseState.Success(response)
    }

    override suspend fun getMovieImages(movieId: Long): ResponseState<MovieImagesResponse> {
        val response = movieApiService.getMovieImages(movieId)
        return ResponseState.Success(response)
    }
}