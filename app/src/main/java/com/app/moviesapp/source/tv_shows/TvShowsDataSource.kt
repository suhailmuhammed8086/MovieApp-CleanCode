package com.app.moviesapp.source.tv_shows

import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import com.app.moviesapp.states.ResponseState

interface TvShowsDataSource {
    suspend fun getDiscoverTvShowList(): ResponseState<TvShowListResponse>

}