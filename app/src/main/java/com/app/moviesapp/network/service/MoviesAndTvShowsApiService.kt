package com.app.moviesapp.network.service

import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import retrofit2.http.GET

interface MoviesAndTvShowsApiService {
    // Add API functions here

    @GET(URL_DISCOVER_MOVIES)
    suspend fun getDiscoverMovies(): MoviesListResponse
    @GET(URL_DISCOVER_TV_SHOWS)
    suspend fun getDiscoverTvShows(): TvShowListResponse

    companion object {
        // Add API urls here

        // Movies
        private const val URL_DISCOVER_MOVIES = "discover/movie"

        // TvShows
        private const val URL_DISCOVER_TV_SHOWS = "discover/tv"
    }
}