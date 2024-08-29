package com.app.moviesapp.network.service

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MovieImagesResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowDetailsResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAndTvShowsApiService {
    // Add API functions here


    // MOVIES
    @GET(URL_DISCOVER_MOVIES)
    suspend fun getDiscoverMovies(@Query(PARAM_WITH_GENRE) genreIds: String?): MoviesListResponse
    @GET(URL_MOVIES_DETAILS)
    suspend fun getMovieDetails(@Path(PARAM_MOVIE_ID) movieId: Long): MovieDetailsResponse
    @GET(URL_MOVIES_GENRES)
    suspend fun getMovieGenreList(): GenreListResponse

    @GET(URL_MOVIES_NOW_PLAYING)
    suspend fun getNowPlayingMovies(): MoviesListResponse

    @GET(URL_MOVIES_POPULAR)
    suspend fun getPopularMovies(): MoviesListResponse

    @GET(URL_MOVIES_TOP_RATED)
    suspend fun getTopRatedMovies(): MoviesListResponse

    @GET(URL_MOVIES_UP_COMING)
    suspend fun getUpComingMovies(): MoviesListResponse
    @GET(URL_MOVIES_IMAGES)
    suspend fun getMovieImages(@Path(PARAM_MOVIE_ID) movieId: Long): MovieImagesResponse



    @GET(URL_DISCOVER_TV_SHOWS)
    suspend fun getDiscoverTvShows(): TvShowListResponse
    @GET(URL_DISCOVER_TV_SHOW_DETAILS)
    suspend fun getTvShowDetails(@Path("tvShowId") tvShowId: Int): TvShowDetailsResponse

    @GET(URL_TV_SHOWS_GENRES)
    suspend fun getTvGenreList(): GenreListResponse

    companion object {

        // Params
        private const val PARAM_MOVIE_ID = "movieId"
        private const val PARAM_WITH_GENRE = "with_genres"

        // Add API urls here
        // Movies
        private const val URL_MOVIES_DETAILS = "movie/{$PARAM_MOVIE_ID}"
        private const val URL_DISCOVER_MOVIES = "discover/movie"
        private const val URL_MOVIES_GENRES = "genre/movie/list"
        private const val URL_MOVIES_NOW_PLAYING = "movie/now_playing"
        private const val URL_MOVIES_POPULAR = "movie/popular"
        private const val URL_MOVIES_TOP_RATED = "movie/top_rated"
        private const val URL_MOVIES_UP_COMING = "movie/upcoming"
        private const val URL_MOVIES_IMAGES = "movie/{$PARAM_MOVIE_ID}/images?language=en"

        // TvShows
        private const val URL_DISCOVER_TV_SHOW_DETAILS= "tv/tvShowId"
        private const val URL_DISCOVER_TV_SHOWS = "discover/tv"
        private const val URL_TV_SHOWS_GENRES = "genre/tv/list"



    }

}
