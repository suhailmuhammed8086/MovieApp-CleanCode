package com.app.moviesapp.repository.tv

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowDetailsResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import com.app.moviesapp.states.ResponseState

interface TvShowRepository  {

    suspend fun getDiscoverTvShowList(): ResponseState<TvShowListResponse>
    suspend fun getTvShowDetails(tvShowId: Int): ResponseState<TvShowDetailsResponse>
    suspend fun getTvShowsGenreList(): ResponseState<GenreListResponse>

}