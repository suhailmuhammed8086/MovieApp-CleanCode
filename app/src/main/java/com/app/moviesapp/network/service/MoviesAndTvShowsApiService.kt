package com.app.moviesapp.network.service

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowDetailsResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesAndTvShowsApiService {
    // Add API functions here


    // MOVIES
    @GET(URL_DISCOVER_MOVIES)
    suspend fun getDiscoverMovies(): MoviesListResponse
    @GET(URL_MOVIES_DETAILS)
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetailsResponse
    @GET(URL_MOVIES_GENRES)
    suspend fun getMovieGenreList(): GenreListResponse



    @GET(URL_DISCOVER_TV_SHOWS)
    suspend fun getDiscoverTvShows(): TvShowListResponse
    @GET(URL_DISCOVER_TV_SHOW_DETAILS)
    suspend fun getTvShowDetails(@Path("tvShowId") tvShowId: Int): TvShowDetailsResponse

    @GET(URL_TV_SHOWS_GENRES)
    suspend fun getTvGenreList(): GenreListResponse

    companion object {
        // Add API urls here

        // Movies
        private const val URL_MOVIES_DETAILS = "movie/{movieId}"
        private const val URL_DISCOVER_MOVIES = "discover/movie"
        private const val URL_MOVIES_GENRES = "genre/movie/list"

        // TvShows
        private const val URL_DISCOVER_TV_SHOW_DETAILS= "tv/tvShowId"
        private const val URL_DISCOVER_TV_SHOWS = "discover/tv"
        private const val URL_TV_SHOWS_GENRES = "genre/tv/list"
    }
}