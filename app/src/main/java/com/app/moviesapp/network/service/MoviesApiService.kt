package com.app.moviesapp.network.service

import com.app.moviesapp.network.model.response.DiscoverMoviesListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MoviesApiService {
    // Add API functions here

    @GET(URL_DISCOVER_MOVIES)
    suspend fun getDiscoverMovies(): DiscoverMoviesListResponse

    companion object {
        // Add API urls here
        private const val URL_DISCOVER_MOVIES = "discover/movie"
    }
}