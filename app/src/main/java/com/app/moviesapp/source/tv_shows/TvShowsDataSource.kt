package com.app.moviesapp.source.tv_shows

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowDetailsResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import com.app.moviesapp.states.ResponseState

interface TvShowsDataSource {
    suspend fun getDiscoverTvShowList(): ResponseState<TvShowListResponse>
    suspend fun getTvShowDetail(tvShowId: Int): ResponseState<TvShowDetailsResponse>
    suspend fun getTvShowsGenreList(): ResponseState<GenreListResponse>

}