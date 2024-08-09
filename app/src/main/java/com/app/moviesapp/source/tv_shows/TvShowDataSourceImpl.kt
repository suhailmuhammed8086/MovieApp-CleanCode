package com.app.moviesapp.source.tv_shows

import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import com.app.moviesapp.network.service.MoviesAndTvShowsApiService
import com.app.moviesapp.states.ResponseState
import javax.inject.Inject

class TvShowDataSourceImpl @Inject constructor(
    private val movieApiService: MoviesAndTvShowsApiService
) : TvShowsDataSource {
    override suspend fun getDiscoverTvShowList(): ResponseState<TvShowListResponse> {
        val response = movieApiService.getDiscoverTvShows()
        return ResponseState.Success(response)
    }
}