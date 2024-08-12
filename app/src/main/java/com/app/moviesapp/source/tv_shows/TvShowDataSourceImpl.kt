package com.app.moviesapp.source.tv_shows

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowDetailsResponse
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
    override suspend fun getTvShowDetail(tvShowId: Int): ResponseState<TvShowDetailsResponse> {
        val response = movieApiService.getTvShowDetails(tvShowId)
        return ResponseState.Success(response)
    }
    override suspend fun getTvShowsGenreList(): ResponseState<GenreListResponse> {
        val response = movieApiService.getTvGenreList()
        return ResponseState.Success(response)
    }
}